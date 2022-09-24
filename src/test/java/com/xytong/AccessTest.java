package com.xytong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.DTO.AccessRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.FileService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static com.xytong.utils.SecurityUtils.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AccessTest {
    @Autowired
    AccessService accessService;
    @Autowired
    FileService fileService;

    String readFile(String url) {
        return fileService.readFile(url);
    }

    @Test
    public void fileReadTest() {
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        logger.info(readFile("classpath:access/rsa_token"));
        assertNotNull(readFile("classpath:access/rsa_token"));
    }

    @Test
    public void rsaTest() {
        String userName = "bszydxh";
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        try {
            assertEquals(userName, rsaDecrypt(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub"))
                    , readFile("classpath:access/rsa_token")));//测试密钥配对性
            logger.info(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")));
            assertEquals(userName, rsaDecrypt(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")), readFile("classpath:access/rsa_token")));
            logger.info(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void md5Test() {
        String userName = "bszydxh";
        String pwd = "1357924680";
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        assertEquals("0e07eacaf3810da8b19613986ff20fc5", md5(userName));
        logger.info(md5(userName));
        assertEquals("227695cd8ea3b7194e9c2cbd9eba4342", md5Salt(userName, pwd));
        logger.info(md5Salt(userName, pwd));
    }

    @Test
    public void tokenMakerTest() throws Exception {
        String userName = "bszydxh";
        String pwdMd5 = "227695cd8ea3b7194e9c2cbd9eba4342";
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        String token = accessService.tokenMaker(userName, pwdMd5, System.currentTimeMillis());
        logger.info(token);
        String json = rsaDecrypt(token, readFile("classpath:access/rsa_token"));
        logger.info(json);
        ObjectMapper objectMapper = new ObjectMapper();
        AccessRequestDTO accessRequestDTO = objectMapper.readValue(json, AccessRequestDTO.class);
        assertEquals(userName, accessRequestDTO.getUsername());
        assertEquals(pwdMd5, accessRequestDTO.getPassword());
    }

    @Test
    public void tokenCheckerTest() throws Exception {
        String userName = "bszydxh";
        String pwdMd5 = "227695cd8ea3b7194e9c2cbd9eba4342";
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        String token = accessService.tokenMaker(userName, pwdMd5, System.currentTimeMillis());
        assertTrue(accessService.tokenChecker(token));
        String token_illegal = accessService.tokenMaker(userName + "_illegal", pwdMd5, System.currentTimeMillis());
        assertFalse(accessService.tokenChecker(token_illegal));
    }


    @Test
    public void tokenRenewerTest() throws Exception {
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        String oldToken = accessService.tokenMaker("bszydxh", "227695cd8ea3b7194e9c2cbd9eba4342",
                System.currentTimeMillis());
        logger.info(oldToken);
        assertNotNull(oldToken);
        String newToken = accessService.tokenRenewer(oldToken);
        logger.info(newToken);
        assertNotNull(newToken);
        String json = rsaDecrypt(newToken, readFile("classpath:access/rsa_token"));
        ObjectMapper mapper = new ObjectMapper();
        AccessRequestDTO accessRequestDTO = mapper.readValue(json, AccessRequestDTO.class);
        logger.info(newToken);
        assertEquals("bszydxh", accessRequestDTO.getUsername());

    }
}
