package com.xytong.service;

import com.xytong.model.bo.CommentBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void getCommentListTest() {
        List<CommentBO> commentList = commentService.getCommentList(
                1587386242887917569L,
                "forums",
                "newest",
                System.currentTimeMillis(),
                0,
                0
        );
        log.info(commentList.toString());
        assertNotNull(commentList.get(0));

    }

    @Test
    void addCommentsTest() {
        CommentBO commentBO = new CommentBO();
        commentBO.setModule(FORUM_MODULE_NAME);
        commentBO.setText("test");
        commentBO.setCid(1588439703469469698L);
        commentBO.setTimestamp(System.currentTimeMillis());
        commentBO.setUserName("bszydxh");
        commentService.addComment(commentBO);
    }
}