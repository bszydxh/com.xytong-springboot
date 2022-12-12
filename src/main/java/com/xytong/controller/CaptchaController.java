package com.xytong.controller;

import com.xytong.model.dto.captcha.CaptchaSendRequestDTO;
import com.xytong.model.dto.captcha.CaptchaSendResponseDTO;
import com.xytong.service.CaptchaService;
import com.xytong.utils.CaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bszydxh
 */
@Controller
public class CaptchaController {
    final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public static final String CAPTCHA_MODULE_NAME = "captcha";

    @RequestMapping(value = "/" + CAPTCHA_MODULE_NAME + "/v1/send", produces = "application/json")
    @ResponseBody
    public CaptchaSendResponseDTO sendEmail(@RequestBody CaptchaSendRequestDTO captchaSendRequestDTO) {
        CaptchaSendResponseDTO captchaSendResponseDTO = new CaptchaSendResponseDTO();
        captchaSendResponseDTO.setTimestamp(System.currentTimeMillis());
        if (captchaSendRequestDTO.getTimestamp() == null) {
            captchaSendResponseDTO.setMode("timestamp error");
            return captchaSendResponseDTO;
        }
        if (captchaSendRequestDTO.getEmail() == null) {
            captchaSendResponseDTO.setMode("email error");
            return captchaSendResponseDTO;
        }
        if (captchaService.sendCaptchaCodeWithStore(
                CaptchaUtils.getCaptchaCode(7),
                captchaSendRequestDTO.getEmail(),
                captchaSendRequestDTO.getTimestamp()
        )) {
            captchaSendResponseDTO.setMode("send ok");
        } else {
            captchaSendResponseDTO.setMode("send error");
        }
        return captchaSendResponseDTO;
    }
}
