package com.xytong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.controllerData.json.AccessRequestJson;
import com.xytong.service.AccessService;
import com.xytong.service.FileService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        logger.info(readFile("classpath:access/rsa_key"));
        assertNotNull(readFile("classpath:access/rsa_key"));
    }

    @Test
    public void rsaTest() {
        String userName = "bszydxh";
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        try {
            assertEquals(userName, rsaDecrypt(rsaEncrypt(userName, readFile("classpath:access/rsa_key.pub"))
                    , readFile("classpath:access/rsa_key")));//测试密钥配对性
            logger.info(rsaEncrypt(userName, readFile("classpath:access/rsa_key.pub")));
            assertEquals(userName, rsaDecrypt(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")), readFile("classpath:access/rsa_token")));
            logger.info(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
        AccessRequestJson accessRequestJson = objectMapper.readValue(json, AccessRequestJson.class);
        assertEquals(userName, accessRequestJson.getUsername());
        assertEquals(pwdMd5, accessRequestJson.getPassword());
    }

    @Test
    public void tokenCheckerTest() throws Exception {
        String userName = "bszydxh";
        String pwdMd5 = "227695cd8ea3b7194e9c2cbd9eba4342";
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        String token = accessService.tokenMaker(userName, pwdMd5, System.currentTimeMillis());
        assertTrue(accessService.tokenChecker(token));
        String token_illegal = accessService.tokenMaker(userName + "_illegal", pwdMd5, System.currentTimeMillis());
        assertFalse(accessService.tokenChecker(token_illegal));;
    }


    @Test
    public void tokenRenewerTest() throws Exception {
        Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);
        String token = "YfURrAMoCbqehuyj7QJIGmmuFsei2LAePObZQZNzf4JYFiknhg+4C9/" +
                "o5qgYOd7taYlcfUc5DzrriwSaX8/hoSgT8mePg7QR9pooq6h4YvhgdJ7sHG3E70uPhaN0Ua/kiaGK3bbiEaAnn/" +
                "I7e+Evpf4VQ+8hFF/mWBZloGcWCz0idTNdA3Un5JeVsle2ONEnDR8Szjz/LliT4gvA3SBkQHVzxzw70qpb/" +
                "33+P6WW3+A1drhfY5p6B5TtyGPSUMzcgy2g7jxcWxekwPDIbMExqvTklCbBZm62I8tMEM3pNYqpCkPvC6GqyZAZjWBY3J2Oskomy8QjoQbZ/aOfcV1a7Q==";
        String newToken = accessService.tokenRenewer(token);
        assertNotNull(newToken);
        String json = rsaDecrypt(newToken, readFile("classpath:access/rsa_token"));
        ObjectMapper mapper = new ObjectMapper();
        AccessRequestJson accessRequestJson = mapper.readValue(json, AccessRequestJson.class);
        logger.info(newToken);
        assertEquals("bszydxh", accessRequestJson.getUsername());

    }
}
