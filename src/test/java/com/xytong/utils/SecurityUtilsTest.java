package com.xytong.utils;

import com.xytong.service.AccessService;
import com.xytong.service.FileService;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.xytong.utils.SecurityUtils.*;
import static com.xytong.utils.SecurityUtils.md5Salt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@SpringBootTest
public class SecurityUtilsTest {
    @Autowired
    AccessService accessService;
    @Autowired
    FileService fileService;

    final String userNameTest = "bszydxh";

    final String pwdTest = "227695cd8ea3b7194e9c2cbd9eba4342";

    String readFile(String url) {
        return fileService.readFile(url);
    }

    @Test
    public void rsaTest() {
        try {
            assertEquals(userNameTest, rsaDecrypt(rsaEncrypt(userNameTest, readFile("classpath:access/rsa_token.pub"))
                    , readFile("classpath:access/rsa_token")));//测试密钥配对性
            log.info(rsaEncrypt(userNameTest, readFile("classpath:access/rsa_token.pub")));
            assertEquals(userNameTest, rsaDecrypt(rsaEncrypt(userNameTest, readFile("classpath:access/rsa_token.pub")), readFile("classpath:access/rsa_token")));
            log.info(rsaEncrypt(userNameTest, readFile("classpath:access/rsa_token.pub")));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void md5Test() {
        String pwd = "1357924680";
        assertEquals("0e07eacaf3810da8b19613986ff20fc5", md5(userNameTest));
        log.info(md5(userNameTest));
        assertEquals(pwdTest, md5Salt(userNameTest, pwd));
        log.info(md5Salt(userNameTest, pwd));
    }
}
