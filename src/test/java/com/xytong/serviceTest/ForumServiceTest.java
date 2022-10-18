package com.xytong.serviceTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.po.ForumPO;
import com.xytong.model.po.UserPO;
import com.xytong.service.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class ForumServiceTest {
    @Autowired
    ForumService forumService;
    @Test
    void getForumListTest() {
    }
    @Test
    void addForumTest() {
        ForumBO forumBO = new ForumBO();
        forumBO.setLikes(1);
        forumBO.setComments(2);
        forumBO.setForwarding(3);
        forumBO.setUserName("bszydxh");
        forumBO.setUserAvatarUrl("127.0.0.1");
        forumBO.setTitle("addForumTest");
        forumBO.setText("addForumTest");
        forumBO.setTimestamp(System.currentTimeMillis());
        assertTrue(forumService.addForum(forumBO));
        assertTrue(forumService.addForum(forumBO));
        assertTrue(forumService.addForum(forumBO));
        assertTrue(forumService.addForum(forumBO));
        QueryWrapper<ForumPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "addForumTest");
        forumService.remove(queryWrapper);
    }
}