package com.xytong.utils;

import com.xytong.model.bo.LikeBO;

import javax.validation.constraints.NotNull;

public class RedisUtils {
    public static String likeBO2RedisKey(LikeBO likeBO) {
        StringBuilder builder = new StringBuilder();
        builder.append(likeBO.getModule());
        builder.append("::");
        builder.append(likeBO.getCid());
        builder.append("::");
        builder.append(likeBO.getUid());
        return builder.toString();
    }
    @NotNull
    public static LikeBO redisKey2LikeBO(String id)
    {
        String[] split = id.split("::");
        LikeBO likeBO = new LikeBO();
        likeBO.setModule(split[0]);
        likeBO.setCid(Long.valueOf(split[1]));
        likeBO.setUid(Long.valueOf(split[2]));
        return likeBO;
    }
}
