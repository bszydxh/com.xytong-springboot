package com.xytong.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.bo.TokenBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.dto.access.AccessCheckRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.FileService;
import com.xytong.service.UserService;
import com.xytong.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author bszydxh
 * 对于token校验分发等一系列实现
 * 2022-11-1 22:54:29
 */
@Slf4j
@Service
public class AccessServiceImpl implements AccessService {
    final FileService fileService;
    final UserService userService;

    public AccessServiceImpl(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    public String tokenMaker(String username, String password, Long timestamp) {
        String token = "";
        ObjectMapper postMapper = new ObjectMapper();
        TokenBO tokenBO = new TokenBO();
        tokenBO.setUsername(username);
        tokenBO.setPassword(password);
        tokenBO.setTimestamp(timestamp);
        try {
            token = SecurityUtils.rsaEncrypt(postMapper.writeValueAsString(tokenBO),
                    fileService.readFile("classpath:access/rsa_token.pub"));
        } catch (Exception e) {
            log.error("制作token失败");
        }
        return token;
    }

    public boolean tokenChecker(String token) {
        TokenBO tokenBO = tokenParser(token);
        if (tokenBO == null) {
            return false;
        }
        return userService.checkUser(tokenBO.getUsername(), tokenBO.getPassword());
    }

    @Override
    public boolean tokenCheckerWithUsername(String token, String username) {
        if (!tokenChecker(token)) {
            return false;
        }
        TokenBO tokenBO = tokenParser(token);
        UserBO userBO = userService.findUserByName(tokenBO.getUsername());
        if (userBO == null) {
            log.error("token 含有非法用户，疑似密钥泄露！");
            return false;
        }
        if (userBO.getAdmin().equals(true)) {
            log.warn("Admin pass");
            return true;
        }
        return Objects.equals(userBO.getName(), username);
    }


    @Override
    public String tokenRenewer(String token) {
        if (tokenChecker(token)) {
            try {
                String jsonStr = SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token"));
                ObjectMapper objectMapper = new ObjectMapper();
                AccessCheckRequestDTO TokenBO = objectMapper.readValue(jsonStr, AccessCheckRequestDTO.class);
                return tokenMaker(
                        TokenBO.getUsername(),
                        TokenBO.getPassword(),
                        System.currentTimeMillis()
                );
            } catch (Exception e) {
                log.error("token更新失败");
            }
        }
        return null;
    }

    @Override
    public TokenBO tokenParser(String token) {
        if (token == null || "".equals(token)) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    SecurityUtils.rsaDecrypt(token, fileService.readFile("classpath:access/rsa_token")),
                    TokenBO.class);
        } catch (Exception e) {
            log.error("检测到非法token");
        }
        return null;
    }


}
