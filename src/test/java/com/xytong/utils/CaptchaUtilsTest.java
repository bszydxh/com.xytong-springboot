package com.xytong.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class CaptchaUtilsTest {
    @Test
    void getCaptchaCodeTest() {
        String captchaCode = CaptchaUtils.getCaptchaCode(8);
        log.info("Captcha code: " + captchaCode);
        assertNotNull(captchaCode);
    }
}
