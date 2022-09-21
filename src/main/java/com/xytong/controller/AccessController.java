package com.xytong.controller;

import com.xytong.model.controllerData.json.AccessPostJson;
import com.xytong.model.controllerData.json.AccessRequestJson;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public AccessPostJson accessRequestMapping(@RequestBody AccessRequestJson accessRequestJson) {
        AccessPostJson accessPostJson = new AccessPostJson();
        Logger logger = LoggerFactory.getLogger(this.getClass());
        if (accessRequestJson.getToken() == null || Objects.equals(accessRequestJson.getToken(), "")) {
            if (userService.checkUser(accessRequestJson.getUsername(), accessRequestJson.getPassword())) {
                accessPostJson.setToken(accessService.tokenMaker(
                        accessRequestJson.getUsername(),
                        accessRequestJson.getPassword(),
                        System.currentTimeMillis()));
            }
            accessPostJson.setUsername(accessRequestJson.getUsername());
            accessPostJson.setTimestamp(System.currentTimeMillis());
        } else {
            AccessRequestJson accessRequestJson_token = accessService.tokenParser(accessRequestJson.getToken());
            accessPostJson.setToken(accessService.tokenRenewer(accessRequestJson.getToken()));
            accessPostJson.setUsername(accessRequestJson_token.getUsername());
            accessPostJson.setTimestamp(System.currentTimeMillis());
        }
        return accessPostJson;
    }
}
