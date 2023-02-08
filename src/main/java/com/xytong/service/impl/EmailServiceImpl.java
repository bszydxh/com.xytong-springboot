package com.xytong.service.impl;


import com.xytong.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * @author bszydxh
 */

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    final JavaMailSender mailSender;

    // 配置文件中我的qq邮箱
    @Value("${spring.mail.from}")
    private String from;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public boolean sendSimpleMail(@NotNull String to, @NotNull String subject, @NotNull String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
            log.info("send ok!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("send error");
        }
        return false;
    }

    /**
     * html邮件
     *
     * @param to      收件人,多个时参数形式 ："xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public boolean sendHtmlMail(@NotNull String to, @NotNull String subject, @NotNull String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            InternetAddress[] internetAddressTo = InternetAddress.parse(to);
            messageHelper.setTo(internetAddressTo);
            message.setSubject(subject);
            messageHelper.setText(content, true);
            mailSender.send(message);
            log.info("send ok!");
            return true;
        } catch (Exception e) {
            log.error("发送邮件时发生异常！", e);
        }
        return false;
    }

    @Override
    public boolean sendCaptchaMail(@NotNull String to, @NotNull String captchaCode) {
        return sendHtmlMail(to, "校园通注册", "<h1>欢迎注册校园通服务</h1><p>您的验证码为" + captchaCode + "\n，验证码于5分钟内有效，请不要告诉他人</p>");
    }
}
