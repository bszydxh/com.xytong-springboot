package com.xytong.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class WithoutSpringTest {
    @Test
    void getTimestamp() {
        log.info(String.valueOf(System.currentTimeMillis()));
    }
}
