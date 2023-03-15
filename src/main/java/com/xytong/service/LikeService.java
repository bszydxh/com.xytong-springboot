package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.LikeBO;
import com.xytong.model.po.LikePO;

/**
 * @author bszydxh
 * 点赞模块主要对论坛和评论负责
 */
public interface LikeService extends IService<LikePO> {
    boolean addLike(LikeBO likeBO);

    boolean deleteLikeByCid(Long cid);

    boolean deleteLikeByUid(Long uid);

    /**
     * 根据传入的likeBO智能判断要删除的的数据
     *
     * @param likeBO like业务类型
     * @return 执行结果
     */
    boolean deleteLike(LikeBO likeBO);

    void transLikedFromRedis2DB();

    void transLikedCountFromRedis2DB();
}
