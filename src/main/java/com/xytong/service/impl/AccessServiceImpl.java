package com.xytong.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.controllerData.json.AccessRequestJson;
import com.xytong.service.FileService;
import com.xytong.service.UserService;
import com.xytong.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements com.xytong.service.AccessService {
    final FileService fileService;
    final UserService userService;

    public AccessServiceImpl(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    public String tokenMaker(String username, String password, Long timestamp) {
        String token = "";
        ObjectMapper postMapper = new ObjectMapper();
        AccessRequestJson accessRequestJson = new AccessRequestJson();
        accessRequestJson.setUsername(username);
        accessRequestJson.setPassword(password);
        accessRequestJson.setTimestamp(timestamp);
        try {
            token = SecurityUtils.rsaEncrypt(postMapper.writeValueAsString(accessRequestJson),
                    fileService.readFile("classpath:access/rsa_token.pub"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean tokenChecker(String token) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            AccessRequestJson accessRequestJson = objectMapper.readValue(
//                    SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token")),
//                    AccessRequestJson.class);
//            return userService.checkUser(accessRequestJson.getUsername(), accessRequestJson.getPassword());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        AccessRequestJson accessRequestJson = tokenParser(token);
        return userService.checkUser(accessRequestJson.getUsername(), accessRequestJson.getPassword());
    }


    @Override
    public String tokenRenewer(String token) {
        if (tokenChecker(token)) {
            try {
                String jsonStr = SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token"));
                ObjectMapper objectMapper = new ObjectMapper();
                AccessRequestJson accessRequestJson = objectMapper.readValue(jsonStr, AccessRequestJson.class);
                return tokenMaker(
                        accessRequestJson.getUsername(),
                        accessRequestJson.getPassword(),
                        System.currentTimeMillis()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public AccessRequestJson tokenParser(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token")),
                    AccessRequestJson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AccessRequestJson();
    }


}
