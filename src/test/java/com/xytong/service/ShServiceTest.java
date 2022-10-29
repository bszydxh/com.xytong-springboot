package com.xytong.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xytong.model.bo.ShBO;
import com.xytong.model.bo.ShBO;
import com.xytong.model.bo.ShBO;
import com.xytong.model.po.ShPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ShServiceTest {
    @Autowired
    ShService shService;

    @Test
    void getShList() {
        List<ShBO> shBOList = shService.getShList("newest", System.currentTimeMillis(), 0, 4);
        assertEquals(shBOList.size(), 5);
        log.info("Sh list size: " + shBOList.size());
        assertNotNull(shBOList.get(0));
        log.info("Sh first shBO: " + shBOList.get(0).toString());
        assertNotNull(shBOList.get(0).getText());
        shBOList = shService.getShList("newest", System.currentTimeMillis(), 0, 0);
        assertTrue(shBOList.size() > 0);
        log.info("Sh list size: " + shBOList.size());
        assertNotNull(shBOList.get(0));
        log.info("Sh first shBO: " + shBOList.get(0).toString());
        assertNotNull(shBOList.get(0).getText());
    }

    @Test
    void addShTest() {
        ShBO shBO = new ShBO();
        shBO.setPrice("4.22");
        shBO.setUserName("bszydxh");
        shBO.setUserAvatarUrl("127.0.0.1");
        shBO.setTitle("addShTest");
        shBO.setImageUrl("127.0.0.1");
        shBO.setText("addShTest");
        shBO.setTimestamp(System.currentTimeMillis());
        assertTrue(shService.addSh(shBO));
        ShBO shBO2 = new ShBO();
        shBO.setPrice("4.22");
        shBO2.setUserName("illegal");
        shBO2.setUserAvatarUrl("127.0.0.1");
        shBO2.setTitle("addShTest");
        shBO2.setImageUrl("127.0.0.1");
        shBO2.setText("addShTest");
        shBO2.setTimestamp(System.currentTimeMillis());
        assertFalse(shService.addSh(shBO2));
        assertFalse(shService.addSh(null));
        QueryWrapper<ShPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "addShTest");
        shService.remove(queryWrapper);
    }
}