package com.xytong.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class IdUtilsTest {
    @Test
    void isCidValidTest() {
        assertTrue(IdUtils.isCidValid(FORUM_MODULE_NAME,1601129887294103553L));
    }
}
