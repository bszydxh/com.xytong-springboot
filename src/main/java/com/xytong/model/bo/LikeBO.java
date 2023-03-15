package com.xytong.model.bo;

import lombok.Data;

@Data
public class LikeBO {
    String module;
    Long uid;
    Long cid;

    public static LikeBO init(String module, Long cid, Long uid) {
       LikeBO likeBO = new LikeBO();
       likeBO.setUid(uid);
       likeBO.setCid(cid);
       likeBO.setModule(module);
       return likeBO;
    }
}
