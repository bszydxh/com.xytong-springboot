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

    final String userNameTest = "bszydxh";

    final String pwdTest = "227695cd8ea3b7194e9c2cbd9eba4342";
//    final String userNameTest = "xzx2";
//
//    final String pwdTest = "456";
    String readFile(String url) {
        return fileService.readFile(url);
    }

    @Test
    public void fileReadTest() {
        log.info(readFile("classpath:access/rsa_token"));
        assertNotNull(readFile("classpath:access/rsa_token"));
    }

    @Test
    public void tokenMakerTest() throws Exception {
        String token = accessService.tokenMaker(userNameTest, pwdTest, System.currentTimeMillis());
        log.info(token);
        String json = rsaDecrypt(token, readFile("classpath:access/rsa_token"));
        log.info(json);
        ObjectMapper objectMapper = new ObjectMapper();
        AccessRequestDTO accessRequestDTO = objectMapper.readValue(json, AccessRequestDTO.class);
        assertEquals(userNameTest, accessRequestDTO.getUsername());
        assertEquals(pwdTest, accessRequestDTO.getPassword());
    }

    @Test
    public void tokenCheckerTest() throws Exception {
        String token = accessService.tokenMaker(userNameTest, pwdTest, System.currentTimeMillis());
        log.info(token);
        assertTrue(accessService.tokenChecker(token));
        String token_illegal = accessService.tokenMaker(userNameTest + "_illegal", pwdTest, System.currentTimeMillis());
        log.info(token_illegal);
        assertFalse(accessService.tokenChecker(token_illegal));
    }


    @Test
    public void tokenRenewerTest() throws Exception {
        String oldToken = accessService.tokenMaker(userNameTest, pwdTest,
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
        assertEquals(userNameTest, accessRequestDTO.getUsername());
    }

    @Test
    public void tokenParserTest() {
        String oldToken = accessService.tokenMaker(userNameTest, pwdTest,
                System.currentTimeMillis());
        assertEquals(userNameTest, accessService.tokenParser(oldToken).getUsername());
    }
}
