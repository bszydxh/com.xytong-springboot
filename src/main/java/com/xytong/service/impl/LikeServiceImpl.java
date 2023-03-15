package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.LikeMapper;
import com.xytong.model.bo.LikeBO;
import com.xytong.model.po.LikePO;
import com.xytong.service.*;
import com.xytong.utils.IdUtils;
import com.xytong.utils.NameUtils;
import com.xytong.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author bszydxh
 * 将要实现用redis重写以实现点赞高并发
 */
@Slf4j
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, LikePO>
        implements LikeService {
    private static final String LIKE_REDIS_KEY = "likes";
    final CommentService commentService;
    final ShService shService;
    final ReService reService;
    final ForumService forumService;
    final UserService userService;
    final RedisTemplate<String, Object> redisTemplate;

    public LikeServiceImpl(CommentService commentService, ShService shService, ReService reService, ForumService forumService, UserService userService, RedisTemplate<String, Object> redisTemplate) {
        this.commentService = commentService;
        this.shService = shService;
        this.reService = reService;
        this.forumService = forumService;
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean addLike(LikeBO likeBO) {//用redis重写
        if (likeBO == null) {
            log.warn("likeBO null! check the code!");
            return false;
        }
        if (!IdUtils.isCidValid(likeBO.getModule(), likeBO.getCid())) {
            log.warn("cid invalid");
            return false;
        }
        ////////////以上拦截部分////////////
        String redisHashKey = RedisUtils.likeBO2RedisKey(likeBO);
        HashOperations<String, Object, Object> redisTemplateHash = redisTemplate.opsForHash();
        if (redisTemplateHash.hasKey(LIKE_REDIS_KEY, redisHashKey)) {
            log.info("redis has record");
            Object likeObject = redisTemplateHash.get(LIKE_REDIS_KEY, redisHashKey);
            if (likeObject != null && Integer.parseInt(likeObject.toString()) == 1) {
                log.warn("like is exists");
                return false;
            }
            redisTemplateHash.put(LIKE_REDIS_KEY, redisHashKey, 1);
        } else {//mysql逻辑块，被穿透了访问
            log.info("mysql has record");
            redisTemplateHash.put(LIKE_REDIS_KEY, redisHashKey, 1);
            QueryWrapper<LikePO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("card_fkey", likeBO.getCid());
            queryWrapper.eq("user_fkey", likeBO.getUid());
            queryWrapper.eq(
                    "card_ftable",
                    NameUtils.module2Table(likeBO.getModule()));//点赞表查询记录是否存在，该表显然可以逻辑删除
            if (getOne(queryWrapper) != null) {
                log.warn("like is exists");//点赞存在
                return false;
            }
        }
        //不直接存在mysql中
        return true;
    }

    @Override
    @Deprecated
    public boolean deleteLikeByCid(Long cid) {
        return false;
    }

    @Override
    @Deprecated
    public boolean deleteLikeByUid(Long uid) {
        return false;
    }

    @Override
    public boolean deleteLike(LikeBO likeBO) {
        if (likeBO == null) {
            log.warn("likeBO null! check the code!");
            return false;
        }
        if (!IdUtils.isCidValid(likeBO.getModule(), likeBO.getCid())) {
            log.warn("cid is not valid");
            return false;
        }
        String redisHashKey = RedisUtils.likeBO2RedisKey(likeBO);
        HashOperations<String, Object, Object> redisTemplateHash = redisTemplate.opsForHash();
        if (redisTemplateHash.hasKey(LIKE_REDIS_KEY, redisHashKey)) {
            log.info("redis has record");
            Object likeObject = redisTemplateHash.get(LIKE_REDIS_KEY, redisHashKey);
            if (likeObject != null && Integer.parseInt(likeObject.toString()) == 0) {
                log.warn("like is null");
                return false;
            }
            redisTemplateHash.put(LIKE_REDIS_KEY, redisHashKey, 0);
        } else {
            log.info("mysql has record");
            redisTemplateHash.put(LIKE_REDIS_KEY, redisHashKey, 0);
            QueryWrapper<LikePO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("card_fkey", likeBO.getCid());
            queryWrapper.eq("user_fkey", likeBO.getUid());
            queryWrapper.eq(
                    "card_ftable",
                    NameUtils.module2Table(likeBO.getModule()));//点赞表查询记录是否存在，该表显然可以逻辑删除
            if (getOne(queryWrapper) == null) {
                log.warn("like is null");//点赞不存在
                return false;
            }
        }
        return true;
    }

    @Override
    public void transLikedFromRedis2DB() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(LIKE_REDIS_KEY, ScanOptions.NONE);
        List<LikeBO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedPostId
            LikeBO likeBO = RedisUtils.redisKey2LikeBO(key);
            Integer value = (Integer) entry.getValue();
            redisTemplate.opsForHash().delete(LIKE_REDIS_KEY, key);//删除key
            if (value == 0) {
                QueryWrapper<LikePO> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("card_fkey", likeBO.getCid());
                queryWrapper.eq("user_fkey", likeBO.getUid());
                queryWrapper.eq(
                        "card_ftable",
                        NameUtils.module2Table(likeBO.getModule()));
                remove(queryWrapper);
            } else if (value == 1) {
                LikePO likePO = new LikePO();
                likePO.setCardFkey(likeBO.getCid());
                likePO.setCardFtable(NameUtils.module2Table(likeBO.getModule()));
                likePO.setUserFkey(likeBO.getUid());
                try {
                    Date date = new Date(System.currentTimeMillis());
                    likePO.setTimestamp(date);
                } catch (Exception e) {
                    log.warn("not a valid date", e);
                }
                try {
                    save(likePO);
                } catch (Exception e) {
                    log.error("save error", e);
                }
            } else {
                log.error("dirty data!");
            }


        }
    }

    @Override
    public void transLikedCountFromRedis2DB() {

    }
}
