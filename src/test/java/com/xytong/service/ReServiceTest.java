package com.xytong.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xytong.model.bo.ReBO;
import com.xytong.model.bo.ReBO;
import com.xytong.model.bo.ReBO;
import com.xytong.model.po.RePO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ReServiceTest {
    @Autowired
    ReService reService;

    @Test
    void getReList() {
        List<ReBO> reBOList = reService.getReList("newest", System.currentTimeMillis(), 0, 4);
        assertEquals(reBOList.size(), 5);
        log.info("Re list size: " + reBOList.size());
        assertNotNull(reBOList.get(0));
        log.info("Re first reBO: " + reBOList.get(0).toString());
        assertNotNull(reBOList.get(0).getText());
        reBOList = reService.getReList("newest", System.currentTimeMillis(), 0, 0);
        assertTrue(reBOList.size() > 0);
        log.info("Re list size: " + reBOList.size());
        assertNotNull(reBOList.get(0));
        log.info("Re first reBO: " + reBOList.get(0).toString());
        assertNotNull(reBOList.get(0).getText());
    }

    @Test
    void addReTest() {
        ReBO reBO = new ReBO();
        reBO.setPrice("1.11");
        reBO.setUserName("bszydxh");
        reBO.setUserAvatarUrl("127.0.0.1");
        reBO.setTitle("addReTest");
        reBO.setText("addReTest");
        reBO.setTimestamp(System.currentTimeMillis());
        assertTrue(reService.addRe(reBO));
        ReBO reBO2 = new ReBO();
        reBO2.setPrice("1.11");
        reBO2.setUserName("illegal");
        reBO2.setUserAvatarUrl("127.0.0.1");
        reBO2.setTitle("addReTest");
        reBO2.setText("addReTest");
        reBO2.setTimestamp(System.currentTimeMillis());
        assertFalse(reService.addRe(reBO2));
        assertFalse(reService.addRe(null));
        QueryWrapper<RePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "addReTest");
        reService.remove(queryWrapper);
    }
}