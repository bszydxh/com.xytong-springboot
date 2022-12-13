package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.LikeMapper;
import com.xytong.model.bo.LikeBO;
import com.xytong.model.po.LikePO;
import com.xytong.service.*;
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

    public LikeServiceImpl(CommentService commentService, ShService shService, ReService reService, ForumService forumService) {
        this.commentService = commentService;
        this.shService = shService;
        this.reService = reService;
        this.forumService = forumService;
    }

    @Override
    public Boolean addLike(LikeBO likeBO) {
        if (likeBO == null) {
            log.warn("likeBO null! check the code!");
            return false;
        }
        //好像之前都是不信任状态，照旧
        LikePO likePO = new LikePO();
        if (likeBO.getCid() == null) {
            log.warn("cid null");
            return false;
        }
        likePO.setCardFkey(likeBO.getCid());
        if (likeBO.getModule() == null) {
            log.warn("module null");
            return false;
        }
        likePO.setCardFtable(likeBO.getModule());
        if (likeBO.getUid() == null) {
            log.warn("uid null");
            return false;
        }
        likePO.setUserFkey(likeBO.getUid());
        try {
            Date date = new Date(likeBO.getTimestamp());
            likePO.setTimestamp(date);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("not a valid date");
            return false;
        }
        save(likePO);
        return true;
    }

    @Override
    public Boolean deleteLikeByCid(Long cid) {
        return null;
    }

    @Override
    public Boolean deleteLikeByUid(Long uid) {
        return null;
    }

    @Override
    public Boolean deleteLike(LikeBO likeBO) {
        return null;
    }
}
