package com.xytong.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.dto.AccessRequestDTO;
import com.xytong.service.FileService;
import com.xytong.service.UserService;
import com.xytong.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
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
            log.error("制作token失败");
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
                log.error("检测到非法token");
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
            log.error("检测到非法token");
        }
        return new AccessRequestDTO();
    }


}
