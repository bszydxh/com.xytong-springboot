package com.xytong.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xytong.model.po.CaptchaPO;
import com.xytong.utils.CaptchaUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CaptchaServiceTest {
    //String email = "2733879291hls@gmail.com";
    String email = "xuan.yuan@yandex.com";
    String code = "testCode";
    @Autowired
    CaptchaService captchaService;

    @Test
    void sendCaptchaCodeWithStoreTest() {
        code = CaptchaUtils.getCaptchaCode(7);
        assertTrue(captchaService.sendCaptchaCodeWithStore(code, email, System.currentTimeMillis()));
        QueryWrapper<CaptchaPO> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        wrapper.last("ORDER BY `id` DESC LIMIT " + 1);
        assertEquals(captchaService.getOne(wrapper).getCode(), code);
        QueryWrapper<CaptchaPO> wrapperDelete = new QueryWrapper<>();
        wrapperDelete.eq("code", code);
        captchaService.remove(wrapperDelete);
    }

    @Test
    void verifyCaptchaCodeTest() {
        assertTrue(captchaService.sendCaptchaCodeWithStore(code, email, System.currentTimeMillis()));
        assertTrue(captchaService.verifyCaptchaCode(code, email, System.currentTimeMillis()));
        assertFalse(captchaService.verifyCaptchaCode("6666666", email, System.currentTimeMillis()));
        QueryWrapper<CaptchaPO> wrapperDelete = new QueryWrapper<>();
        wrapperDelete.eq("code", code);
        captchaService.remove(wrapperDelete);
    }
}