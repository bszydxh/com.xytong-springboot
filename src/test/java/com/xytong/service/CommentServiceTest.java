package com.xytong.service;

import com.xytong.model.bo.CommentBO;
import com.xytong.utils.NameUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void getCommentListTest() {

    }

    @Test
    void addCommentsTest() {
        CommentBO commentBO = new CommentBO();
        commentBO.setFloor(0);
        commentBO.setLikes(0);
        commentBO.setModule(FORUM_MODULE_NAME);
        commentBO.setCid(1601129887294103553L);
        commentBO.setUserName("bszydxh");
        commentBO.setText("test");
        commentBO.setTimestamp(System.currentTimeMillis());
        assertTrue(commentService.addComment(commentBO));
    }
}