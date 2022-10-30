package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.CaptchaMapper;
import com.xytong.model.po.CaptchaPO;
import com.xytong.service.CaptchaService;
import com.xytong.service.EmailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author bszydxh
 * @description 针对表【captcha】的数据库操作Service实现
 * @createDate 2022-10-30 16:09:04
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, CaptchaPO>
        implements CaptchaService {
    final EmailService emailService;

    public CaptchaServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean sendCaptchaCodeWithStore(@NotNull String code, @NotNull String to, @NotNull Long timestamp) {
        if ("".equals(to)) {
            return false;
        }
        try {
            CaptchaPO captchaPO = new CaptchaPO();
            captchaPO.setEmail(to);
            captchaPO.setCode(code);
            try {
                Date date = new Date(timestamp);
                captchaPO.setTimestamp(date);
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("not a valid date");
            }
            save(captchaPO);
        } catch (Exception e) {
            log.error("save error");
            return false;
        }
        return emailService.sendCaptchaMail(to, code);

    }

    @Override
    public boolean verifyCaptchaCode(@NotNull String code, @NotNull String to, @NotNull Long timestamp) {
        QueryWrapper<CaptchaPO> wrapper = new QueryWrapper<>();
        Date date = new Date(timestamp);
        //过滤新数据
        wrapper.le("timestamp", date);
        wrapper.eq("email", to);
        wrapper.last("ORDER BY `id` DESC LIMIT " + 1);
        CaptchaPO captchaPO = getOne(wrapper);
        if (captchaPO == null) {
            return false;
        }
        return Objects.equals(captchaPO.getCode(), code);
    }
}
