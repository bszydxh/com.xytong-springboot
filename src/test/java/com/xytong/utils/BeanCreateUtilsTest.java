package com.xytong.utils;

import com.xytong.service.ForumService;
import com.xytong.service.LikeService;
import com.xytong.service.ReService;
import com.xytong.service.ShService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class BeanCreateUtilsTest {
    @Test
    void getBeanTest() {
        assertNotNull(BeanCreateUtils.getBeanByClass(ForumService.class));
        assertNotNull(BeanCreateUtils.getBeanByClass(ShService.class));
        assertNotNull(BeanCreateUtils.getBeanByClass(ReService.class));
        assertNotNull(BeanCreateUtils.getBeanByClass(LikeService.class));
        assertNotNull(BeanCreateUtils.getApplicationContext());
    }
}
