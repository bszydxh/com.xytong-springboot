package com.xytong.service;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.xytong.model.bo.LikeBO;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.JdkConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class LikeServiceTest {
    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    ForumService forumService;

    @Test
    void LikeTest() {
        LikeBO likeBO = new LikeBO();
        likeBO.setModule(FORUM_MODULE_NAME);
        Long cid = forumService.getForumList(
                "newest",
                System.currentTimeMillis(),
                0,
                0).get(0).getCid();
        Long uid = userService.findUserByName("bszydxh").getId();
        assertNotNull(cid);
        assertNotNull(uid);
        log.info("cid:" + cid);
        log.info("uid:" + uid);
        likeBO.setUid(uid);
        likeBO.setCid(cid);
        assertTrue(likeService.addLike(likeBO));
        assertTrue(likeService.deleteLike(likeBO));
        assertFalse(likeService.deleteLike(likeBO));
        assertTrue(likeService.addLike(likeBO));
        assertFalse(likeService.addLike(likeBO));
    }

    @Test
    void likeSaveTest() {
        likeService.transLikedFromRedis2DB();
    }
}