package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.po.CaptchaPO;
import org.jetbrains.annotations.NotNull;

/**
 * @author bszydxh
 * @description 针对表【captcha】的数据库操作Service
 * @createDate 2022-10-30 16:09:04
 */
public interface CaptchaService extends IService<CaptchaPO> {

    boolean sendCaptchaCodeWithStore(@NotNull String code, @NotNull String to, @NotNull Long timestamp);

    boolean verifyCaptchaCode(@NotNull String code, @NotNull String to, @NotNull Long timestamp);
}
