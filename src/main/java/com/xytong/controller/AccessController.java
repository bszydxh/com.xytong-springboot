package com.xytong.controller;

import com.xytong.model.dto.AccessPostDTO;
import com.xytong.model.dto.AccessRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
    public AccessPostDTO accessRequestMapping(@RequestBody AccessRequestDTO accessRequestDTO) {
        AccessPostDTO accessPostDTO = new AccessPostDTO();
        if (accessRequestDTO.getToken() == null || Objects.equals(accessRequestDTO.getToken(), "")) {//如果传入值没有token
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
            AccessRequestDTO accessRequestDTO_token = accessService.tokenParser(accessRequestDTO.getToken());
            accessPostDTO.setToken(accessService.tokenRenewer(accessRequestDTO.getToken()));
            accessPostDTO.setUsername(accessRequestDTO_token.getUsername());
            accessPostDTO.setTimestamp(System.currentTimeMillis());
        }
        return accessPostDTO;
    }
}
