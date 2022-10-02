package com.xytong.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.dto.AccessRequestDTO;
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
        AccessRequestDTO TokenBO = new AccessRequestDTO();
        TokenBO.setUsername(username);
        TokenBO.setPassword(password);
        TokenBO.setTimestamp(timestamp);
        try {
            token = SecurityUtils.rsaEncrypt(postMapper.writeValueAsString(TokenBO),
                    fileService.readFile("classpath:access/rsa_token.pub"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean tokenChecker(String token) {
        AccessRequestDTO TokenBO = tokenParser(token);
        return userService.checkUser(TokenBO.getUsername(), TokenBO.getPassword());
    }


    @Override
    public String tokenRenewer(String token) {
        if (tokenChecker(token)) {
            try {
                String jsonStr = SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token"));
                ObjectMapper objectMapper = new ObjectMapper();
                AccessRequestDTO TokenBO = objectMapper.readValue(jsonStr, AccessRequestDTO.class);
                return tokenMaker(
                        TokenBO.getUsername(),
                        TokenBO.getPassword(),
                        System.currentTimeMillis()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public AccessRequestDTO tokenParser(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token")),
                    AccessRequestDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AccessRequestDTO();
    }


}
