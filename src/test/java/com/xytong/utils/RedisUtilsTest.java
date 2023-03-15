package com.xytong.utils;

import com.xytong.model.bo.LikeBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class RedisUtilsTest {

    @Test
    void getRedisLikeIdTest() {
        String target = RedisUtils.likeBO2RedisKey(LikeBO.init(FORUM_MODULE_NAME, 1588439703469469698L, 1L));
        log.info(target);
        assertEquals("forums::1588439703469469698::1", target);
    }

    @Test
    void getRedisLikeBOFormIdTest() {
        LikeBO target = RedisUtils.redisKey2LikeBO("forums::1588439703469469698::1");
        log.info(target.toString());
        assertEquals("forums", target.getModule());
        assertEquals(1L, target.getUid());
        assertEquals(1588439703469469698L, target.getCid());
    }
}