package com.xytong.controller;

import com.xytong.model.bo.TokenBO;
import com.xytong.model.dto.access.AccessResponseDTO;
import com.xytong.model.dto.access.AccessRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

    public AccessController(AccessService accessService, UserService userService) {
        this.accessService = accessService;
        this.userService = userService;
    }

    /**
     * 二合一接口 负责用户token校验+换取新token
     * 超级用户鉴权不在这里
     *
     * @param accessRequestDTO 传入单token或者姓名+密码
     * @return 返回token值
     */
    @RequestMapping(value = "/access", produces = "application/json")
    @ResponseBody
    public AccessResponseDTO getToken(@RequestBody AccessRequestDTO accessRequestDTO) {
        AccessResponseDTO accessResponseDTO = new AccessResponseDTO();
        if (accessRequestDTO.getToken() == null || Objects.equals(accessRequestDTO.getToken().trim(), "")) {//如果传入值没有token
            if (userService.checkUser(accessRequestDTO.getUsername(), accessRequestDTO.getPassword())) {//进入密码鉴权
                accessResponseDTO.setToken(accessService.tokenMaker(
                        accessRequestDTO.getUsername(),
                        accessRequestDTO.getPassword(),
                        System.currentTimeMillis()));
                accessResponseDTO.setMode("check ok");
            } else {
                accessResponseDTO.setMode("username or password error");
            }
            accessResponseDTO.setUsername(accessRequestDTO.getUsername());
            accessResponseDTO.setTimestamp(System.currentTimeMillis());
        } else {
            TokenBO tokenBO = accessService.tokenParser(accessRequestDTO.getToken());
            if (tokenBO != null) {
                accessResponseDTO.setToken(accessService.tokenRenewer(accessRequestDTO.getToken()));
                accessResponseDTO.setMode("get ok");
                accessResponseDTO.setUsername(tokenBO.getUsername());
                accessResponseDTO.setTimestamp(System.currentTimeMillis());
            } else {
                accessResponseDTO.setMode("token error");
            }
        }
        return accessResponseDTO;
    }

    @RequestMapping(value = "/access/v1/check", produces = "application/json")
    @ResponseBody
    public AccessResponseDTO getToken2(@RequestBody AccessRequestDTO accessRequestDTO) {
        return getToken(accessRequestDTO);
    }

    @RequestMapping(value = "/access/v1/signup", produces = "application/json")
    @ResponseBody
    public AccessResponseDTO signup(@RequestBody AccessRequestDTO accessRequestDTO) {
        return null;
    }
}
