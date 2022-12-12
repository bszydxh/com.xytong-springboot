package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.LikeBO;
import com.xytong.model.po.LikePO;

public interface LikeService extends IService<LikePO> {
    Boolean addLike(LikeBO likeBO);

    Boolean deleteLikeByCid(Long cid);

    Boolean deleteLikeByUid(Long uid);

    /**
     * 根据传入的likeBO智能判断要删除的的数据
     *
     * @param likeBO like业务类型
     * @return 执行结果
     */
    Boolean deleteLike(LikeBO likeBO);
}
