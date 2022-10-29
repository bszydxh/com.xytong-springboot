package com.xytong.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.po.ForumPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ForumServiceTest {
    @Autowired
    ForumService forumService;

    @Test
    void getForumListTest() {
        List<ForumBO> forumBOList = forumService.getForumList("newest", System.currentTimeMillis(), 0, 4);
        assertEquals(forumBOList.size(), 5);
        log.info("Forum list size: " + forumBOList.size());
        assertNotNull(forumBOList.get(0));
        log.info("Forum first forumBO: " + forumBOList.get(0).toString());
        assertNotNull(forumBOList.get(0).getText());
        forumBOList = forumService.getForumList("newest", System.currentTimeMillis(), 0, 0);
        assertTrue(forumBOList.size() > 0);
        log.info("Forum list size: " + forumBOList.size());
        assertNotNull(forumBOList.get(0));
        log.info("Forum first forumBO: " + forumBOList.get(0).toString());
        assertNotNull(forumBOList.get(0).getText());
    }

    /**
     * 测试接口过滤能力
     */
    @Test
    void getForumListTest2() {
        QueryWrapper<ForumPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "addForumTest");
        forumService.remove(queryWrapper);
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
        String title = forumService.getForumList("newest", System.currentTimeMillis() - 10000, 0, 1).get(0).getTitle();
        assertNotEquals("addForumTest", title);
        forumService.remove(queryWrapper);
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
        ForumBO forumBO2 = new ForumBO();
        forumBO2.setLikes(1);
        forumBO2.setComments(2);
        forumBO2.setForwarding(3);
        forumBO2.setUserName("illegal");
        forumBO2.setUserAvatarUrl("127.0.0.1");
        forumBO2.setTitle("addForumTest");
        forumBO2.setText("addForumTest");
        forumBO2.setTimestamp(System.currentTimeMillis());
        assertFalse(forumService.addForum(forumBO2));
        assertFalse(forumService.addForum(null));
        QueryWrapper<ForumPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "addForumTest");
        forumService.remove(queryWrapper);
    }
}