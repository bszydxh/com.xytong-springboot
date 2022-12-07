package com.xytong.service;

import org.jetbrains.annotations.NotNull;
/**
 * @author bszydxh
 */

public interface EmailService {
    boolean sendSimpleMail(@NotNull String to, @NotNull String subject, @NotNull String content);

    boolean sendHtmlMail(@NotNull String to, @NotNull String subject, @NotNull String content);

    boolean sendCaptchaMail(@NotNull String to, @NotNull String captchaCode);
}
