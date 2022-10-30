package com.xytong.service;

import com.xytong.utils.CaptchaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService mailService;
    String email = "2733879291hls@gmail.com";
    /**
     * 测试发送文本邮件
     */
    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail(
                email,
                "主题：你好普通邮件",
                "内容：第一封邮件");
    }

    @Test
    public void sendHtmlMailTest() {
        mailService.sendHtmlMail(
                email,
                "主题：你好html邮件",
                "<h1>内容：第一封html邮件</h1>");
    }

    @Test
    public void sendCaptchaMailTest() {
        mailService.sendCaptchaMail(email, CaptchaUtils.getCaptchaCode(7));
    }
}
