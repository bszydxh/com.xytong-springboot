package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.LikeMapper;
import com.xytong.model.bo.LikeBO;
import com.xytong.model.po.LikePO;
import com.xytong.service.*;
import com.xytong.utils.CidUtils;
import com.xytong.utils.NameUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author bszydxh
 */
@Slf4j
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, LikePO>
        implements LikeService {
    final CommentService commentService;
    final ShService shService;
    final ReService reService;
    final ForumService forumService;
    final UserService userService;

    public LikeServiceImpl(CommentService commentService, ShService shService, ReService reService, ForumService forumService, UserService userService) {
        this.commentService = commentService;
        this.shService = shService;
        this.reService = reService;
        this.forumService = forumService;
        this.userService = userService;
    }

    @Override
    public Boolean addLike(LikeBO likeBO) {
        if (likeBO == null) {
            log.warn("likeBO null! check the code!");
            return false;
        }
        //好像之前都是不信任状态，照旧
        if (userService.findUserById(likeBO.getUid()) == null) {
            log.warn("user is not exists");
            return false;
        }
        if (!CidUtils.isCidValid(likeBO.getModule(), likeBO.getCid())) {
            return false;
        }
        QueryWrapper<LikePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_fkey", likeBO.getCid());
        queryWrapper.eq("user_fkey", likeBO.getUid());
        queryWrapper.eq(
                "card_ftable",
                NameUtils.module2Table(likeBO.getModule()));
        if (getOne(queryWrapper) != null) {
            log.warn("like is exists");
            return false;
        }
        LikePO likePO = new LikePO();
        likePO.setCardFkey(likeBO.getCid());
        likePO.setCardFtable(NameUtils.module2Table(likeBO.getModule()));
        likePO.setUserFkey(likeBO.getUid());
        try {
            Date date = new Date(likeBO.getTimestamp());
            likePO.setTimestamp(date);
        } catch (Exception e) {
            log.warn("not a valid date", e);
            return false;
        }
        try {
            save(likePO);
        } catch (Exception e) {
            log.error("save error", e);
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public Boolean deleteLikeByCid(Long cid) {
        return null;
    }

    @Override
    @Deprecated
    public Boolean deleteLikeByUid(Long uid) {
        return null;
    }

    @Override
    public Boolean deleteLike(LikeBO likeBO) {
        if (likeBO == null) {
            log.warn("likeBO null! check the code!");
            return false;
        }
        //好像之前都是不信任状态，照旧
        if (userService.findUserById(likeBO.getUid()) == null) {
            log.warn("user is not exists");
            return false;
        }
        if (!CidUtils.isCidValid(likeBO.getModule(), likeBO.getCid())) {
            return false;
        }
        QueryWrapper<LikePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_fkey", likeBO.getCid());
        queryWrapper.eq("user_fkey", likeBO.getUid());
        queryWrapper.eq(
                "card_ftable",
                NameUtils.module2Table(likeBO.getModule()));
        try {
            remove(queryWrapper);
        } catch (Exception e) {
            log.error("delete error", e);
            return false;
        }
        return true;
    }
}
