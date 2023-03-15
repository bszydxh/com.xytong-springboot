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
 * TODO 优化鉴权体验，鉴权时不能鉴一次遍历一次用户表，对于加密过后的密钥解密后符合基本状态的应予以放行
 * TODO 还是性能问题RSA太慢了！！！高并发时的性能瓶颈
 */
@Slf4j
@Service
public class AccessServiceImpl implements AccessService {
    final FileService fileService;
    final UserService userService;

    String rsa_pub;
    String rsa_private;

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
        if (rsa_pub == null) {
            rsa_pub = fileService.readFile("classpath:access/rsa_token.pub");
        }
        try {
            token = SecurityUtils.rsaEncrypt(postMapper.writeValueAsString(tokenBO), rsa_pub);
        } catch (Exception e) {
            log.error("制作token失败");
        }
        return token;
    }

    /**
     * 检验token合法性
     */
    public boolean tokenChecker(String token) {
        TokenBO tokenBO = tokenParser(token);
        if (tokenBO == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean tokenCheckerWithUsername(String token, String username) {
        TokenBO tokenBO = tokenParser(token);
        if (tokenBO == null) {
            return false;
        }
        UserBO userBO = userService.findUserByName(tokenBO.getUsername());
        if (userBO == null) {
            log.error("token 含有非法用户，疑似密钥泄露！");
            return false;
        }
        if (userBO.getAdmin().equals(true)) {
            log.warn("Admin pass");
            return true;
        }
        return Objects.equals(tokenBO.getUsername(), username);
    }


    @Override
    public String tokenRenewer(String token) {
        if (tokenChecker(token)) {
            try {
                if (rsa_private == null) {
                    rsa_private = fileService.readFile("classpath:access/rsa_token");
                }
                String jsonStr = SecurityUtils.rsaDecrypt(token, rsa_private);
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
        if (token == null || "".equals(token.trim())) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (rsa_private == null) {
                log.info("create");
                rsa_private = fileService.readFile("classpath:access/rsa_token");
            }
            return objectMapper.readValue(SecurityUtils.rsaDecrypt(token, rsa_private), TokenBO.class);
        } catch (Exception e) {
            log.error("检测到非法token");
        }
        return null;
    }


}
