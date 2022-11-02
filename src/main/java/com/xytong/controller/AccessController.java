package com.xytong.controller;

import com.xytong.model.bo.TokenBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.dto.access.AccessCheckRequestDTO;
import com.xytong.model.dto.access.AccessCheckResponseDTO;
import com.xytong.model.dto.access.AccessSignupRequestDTO;
import com.xytong.model.dto.access.AccessSignupResponseDTO;
import com.xytong.service.AccessService;
import com.xytong.service.CaptchaService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author bszydxh
 */
@Slf4j
@RestController
public class AccessController {
    final AccessService accessService;
    final UserService userService;
    final CaptchaService captchaService;

    public AccessController(AccessService accessService, UserService userService, CaptchaService captchaService) {
        this.accessService = accessService;
        this.userService = userService;
        this.captchaService = captchaService;
    }

    /**
     * 二合一接口 负责用户token校验+换取新token
     * 超级用户鉴权不在这里
     *
     * @param accessCheckRequestDTO 传入单token或者姓名+密码
     * @return 返回token值
     */
    @RequestMapping(value = "/access", produces = "application/json")
    @ResponseBody
    public AccessCheckResponseDTO getToken(@NotNull @RequestBody AccessCheckRequestDTO accessCheckRequestDTO) {
        AccessCheckResponseDTO accessCheckResponseDTO = new AccessCheckResponseDTO();
        if (accessCheckRequestDTO.getToken() == null || Objects.equals(accessCheckRequestDTO.getToken().trim(), "")) {//如果传入值没有token
            //进入密码鉴权
            if (userService.checkUser(accessCheckRequestDTO.getUsername(), accessCheckRequestDTO.getPassword())) {
                accessCheckResponseDTO.setToken(accessService.tokenMaker(
                        accessCheckRequestDTO.getUsername(),
                        accessCheckRequestDTO.getPassword(),
                        System.currentTimeMillis()));
                accessCheckResponseDTO.setMode("check ok");
            } else {
                accessCheckResponseDTO.setMode("username or password error");
            }
            accessCheckResponseDTO.setUsername(accessCheckRequestDTO.getUsername());
            accessCheckResponseDTO.setTimestamp(System.currentTimeMillis());
        } else {
            //换取token
            TokenBO tokenBO = accessService.tokenParser(accessCheckRequestDTO.getToken());
            if (tokenBO != null) {
                accessCheckResponseDTO.setToken(accessService.tokenRenewer(accessCheckRequestDTO.getToken()));
                accessCheckResponseDTO.setMode("get ok");
                accessCheckResponseDTO.setUsername(tokenBO.getUsername());
                accessCheckResponseDTO.setTimestamp(System.currentTimeMillis());
            } else {
                accessCheckResponseDTO.setMode("token error");
            }
        }
        return accessCheckResponseDTO;
    }

    @RequestMapping(value = "/access/v1/check", produces = "application/json")
    @ResponseBody
    public AccessCheckResponseDTO getToken2(@RequestBody AccessCheckRequestDTO accessCheckRequestDTO) {
        return getToken(accessCheckRequestDTO);
    }

    @RequestMapping(value = "/access/v1/signup", produces = "application/json")
    @ResponseBody
    public AccessSignupResponseDTO signup(@NotNull @RequestBody AccessSignupRequestDTO accessSignupRequestDTO) {
        AccessSignupResponseDTO accessSignupResponseDTO = new AccessSignupResponseDTO();
        log.warn(accessSignupRequestDTO.toString());
        accessSignupResponseDTO.setTimestamp(System.currentTimeMillis());
        accessSignupRequestDTO.setUsername(
                accessSignupRequestDTO.getUsername() == null ? "" : accessSignupRequestDTO.getUsername().trim());
        accessSignupRequestDTO.setPassword(
                accessSignupRequestDTO.getPassword() == null ? "" : accessSignupRequestDTO.getPassword().trim());
        accessSignupRequestDTO.setCaptchaCode(
                accessSignupRequestDTO.getCaptchaCode() == null ? "" : accessSignupRequestDTO.getCaptchaCode().trim());
        accessSignupRequestDTO.setEmail(
                accessSignupRequestDTO.getEmail() == null ? "" : accessSignupRequestDTO.getEmail().trim());
        accessSignupRequestDTO.setTimestamp(
                accessSignupRequestDTO.getTimestamp() == null ? System.currentTimeMillis() : accessSignupRequestDTO.getTimestamp());
        if ("".equals(accessSignupRequestDTO.getUsername())) {
            accessSignupResponseDTO.setMode("name is empty error");
            return accessSignupResponseDTO;
        }
        if ("".equals(accessSignupRequestDTO.getPassword())) {
            accessSignupResponseDTO.setMode("pwd is empty error");
            return accessSignupResponseDTO;
        }
        if ("".equals(accessSignupRequestDTO.getCaptchaCode())) {
            accessSignupResponseDTO.setMode("captcha is empty error");
            return accessSignupResponseDTO;
        }
        if ("".equals(accessSignupRequestDTO.getEmail())) {
            accessSignupResponseDTO.setMode("email is empty error");
            return accessSignupResponseDTO;
        }
        if (!captchaService.verifyCaptchaCode(
                accessSignupRequestDTO.getCaptchaCode(),
                accessSignupRequestDTO.getEmail(),
                accessSignupRequestDTO.getTimestamp())) {
            accessSignupResponseDTO.setMode("captchaCode error");
            return accessSignupResponseDTO;
        }
        if (userService.findUserByName(accessSignupRequestDTO.getUsername()) != null) {
            accessSignupResponseDTO.setMode("user exist error");
            return accessSignupResponseDTO;
        }
        if (userService.findUserByEmail(accessSignupRequestDTO.getEmail()) != null) {
            accessSignupResponseDTO.setMode("email exist error");
            return accessSignupResponseDTO;
        }
        UserBO userBO = new UserBO();
        userBO.setAdmin(false);
        userBO.setName(accessSignupRequestDTO.getUsername());
        userBO.setPhone("");
        userBO.setGender(UserBO.GENDER_UNKNOWN);
        userBO.setEmail(accessSignupRequestDTO.getEmail());
        userBO.setPassword(accessSignupRequestDTO.getPassword());
        userBO.setUserAvatar("");
        userBO.setSignature("");
        if (userService.addUser(userBO)) {
            accessSignupResponseDTO.setMode("add ok");

            accessSignupResponseDTO.setToken(
                    accessService.tokenMaker(
                            accessSignupRequestDTO.getUsername(),
                            accessSignupRequestDTO.getPassword(),
                            accessSignupRequestDTO.getTimestamp()));
        } else {
            accessSignupResponseDTO.setMode("set error");
        }
        return accessSignupResponseDTO;
    }
}
