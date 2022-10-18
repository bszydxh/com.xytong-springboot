package com.xytong.controller;

import com.xytong.model.bo.TokenBO;
import com.xytong.model.dto.access.AccessPostDTO;
import com.xytong.model.dto.access.AccessRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class AccessController {
    final AccessService accessService;
    final UserService userService;

    public AccessController(AccessService accessService, UserService userService) {
        this.accessService = accessService;
        this.userService = userService;
    }

    @RequestMapping(value = "/access", produces = "application/json")
    @ResponseBody
    public AccessPostDTO getToken(@RequestBody AccessRequestDTO accessRequestDTO) {
        AccessPostDTO accessPostDTO = new AccessPostDTO();
        if (accessRequestDTO.getToken() == null || Objects.equals(accessRequestDTO.getToken().trim(), "")) {//如果传入值没有token
            if (userService.checkUser(accessRequestDTO.getUsername(), accessRequestDTO.getPassword())) {//进入密码鉴权
                accessPostDTO.setToken(accessService.tokenMaker(
                        accessRequestDTO.getUsername(),
                        accessRequestDTO.getPassword(),
                        System.currentTimeMillis()));
                accessPostDTO.setMode("");
            } else {
                accessPostDTO.setMode("username or password error");
            }
            accessPostDTO.setUsername(accessRequestDTO.getUsername());
            accessPostDTO.setTimestamp(System.currentTimeMillis());
        } else {
            TokenBO tokenBO = accessService.tokenParser(accessRequestDTO.getToken());
            if (tokenBO != null) {
                accessPostDTO.setToken(accessService.tokenRenewer(accessRequestDTO.getToken()));
                accessPostDTO.setUsername(tokenBO.getUsername());
                accessPostDTO.setTimestamp(System.currentTimeMillis());
            } else {
                accessPostDTO.setMode("token error");
            }
        }
        return accessPostDTO;
    }
}
