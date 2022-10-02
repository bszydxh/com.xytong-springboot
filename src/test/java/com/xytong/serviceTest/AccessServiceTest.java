package com.xytong.serviceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xytong.model.dto.AccessRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.FileService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.xytong.utils.SecurityUtils.*;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest

public class AccessServiceTest {
    @Autowired
    AccessService accessService;
    @Autowired
    FileService fileService;

    String readFile(String url) {
        return fileService.readFile(url);
    }

    @Test
    public void fileReadTest() {
        log.info(readFile("classpath:access/rsa_token"));
        assertNotNull(readFile("classpath:access/rsa_token"));
    }

    @Test
    public void rsaTest() {
        String userName = "bszydxh";
        try {
            assertEquals(userName, rsaDecrypt(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub"))
                    , readFile("classpath:access/rsa_token")));//测试密钥配对性
            log.info(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")));
            assertEquals(userName, rsaDecrypt(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")), readFile("classpath:access/rsa_token")));
            log.info(rsaEncrypt(userName, readFile("classpath:access/rsa_token.pub")));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void md5Test() {
        String userName = "bszydxh";
        String pwd = "1357924680";
        assertEquals("0e07eacaf3810da8b19613986ff20fc5", md5(userName));
        log.info(md5(userName));
        assertEquals("227695cd8ea3b7194e9c2cbd9eba4342", md5Salt(userName, pwd));
        log.info(md5Salt(userName, pwd));
    }

    @Test
    public void tokenMakerTest() throws Exception {
        String userName = "bszydxh";
        String pwdMd5 = "227695cd8ea3b7194e9c2cbd9eba4342";
        String token = accessService.tokenMaker(userName, pwdMd5, System.currentTimeMillis());
        log.info(token);
        String json = rsaDecrypt(token, readFile("classpath:access/rsa_token"));
        log.info(json);
        ObjectMapper objectMapper = new ObjectMapper();
        AccessRequestDTO accessRequestDTO = objectMapper.readValue(json, AccessRequestDTO.class);
        assertEquals(userName, accessRequestDTO.getUsername());
        assertEquals(pwdMd5, accessRequestDTO.getPassword());
    }

    @Test
    public void tokenCheckerTest() throws Exception {
        String userName = "bszydxh";
        String pwdMd5 = "227695cd8ea3b7194e9c2cbd9eba4342";
        String token = accessService.tokenMaker(userName, pwdMd5, System.currentTimeMillis());
        assertTrue(accessService.tokenChecker(token));
        String token_illegal = accessService.tokenMaker(userName + "_illegal", pwdMd5, System.currentTimeMillis());
        assertFalse(accessService.tokenChecker(token_illegal));
    }


    @Test
    public void tokenRenewerTest() throws Exception {
        String oldToken = accessService.tokenMaker("bszydxh", "227695cd8ea3b7194e9c2cbd9eba4342",
                System.currentTimeMillis());
        log.info("oldToken:" + oldToken);
        assertNotNull(oldToken);
        String newToken = accessService.tokenRenewer(oldToken);
        log.info("newToken:" + newToken);
        assertNotNull(newToken);
        String illegalToken = accessService.tokenRenewer("_illegal" + oldToken);
        log.info("illegalToken:" + illegalToken);
        assertNull(illegalToken);
        String json = rsaDecrypt(newToken, readFile("classpath:access/rsa_token"));
        ObjectMapper mapper = new ObjectMapper();
        AccessRequestDTO accessRequestDTO = mapper.readValue(json, AccessRequestDTO.class);
        assertEquals("bszydxh", accessRequestDTO.getUsername());
    }
}
