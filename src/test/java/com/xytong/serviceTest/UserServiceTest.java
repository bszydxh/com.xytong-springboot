package com.xytong.serviceTest;

import com.xytong.model.po.UserPO;
import com.xytong.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void checkUserTest() {
        String pwd = "227695cd8ea3b7194e9c2cbd9eba4342";
        String username = "bszydxh";
        assertTrue(userService.checkUser(username, pwd));
        String pwd2 = "8eab34801b8f05644302ecacb5eadc49";
        String username2 = "xzx";
        assertTrue(userService.checkUser(username2, pwd2));
        String pwd_illegal = "227695cd8ea3b7194e9c2cbd9eba4342_illegal";
        String username_illegal = "bszydxh_illegal";
        assertFalse(userService.checkUser(username, pwd_illegal));
        assertFalse(userService.checkUser(username_illegal, pwd));
    }

    @Test
    public void findUserByNameTest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        UserPO userPO = userService.findUserByName("bszydxh");
        logger.info(userPO.toString());
        assertEquals("bszydxh", userPO.getName());
        UserPO userPO2 = userService.findUserByName("xzx");
        logger.info(userPO.toString());
        assertEquals("xzx", userPO2.getName());
        assertNotNull(userPO2.getPassword());
        assertNotNull(userPO.getPassword());
    }
    @Test
    public void findUserByIdTest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        UserPO userPO = userService.findUserById(1);
        logger.info(userPO.toString());
        assertEquals("bszydxh", userPO.getName());
        UserPO userPO2 = userService.findUserById(2);
        logger.info(userPO.toString());
        assertEquals("xzx", userPO2.getName());
        assertNotNull(userPO2.getPassword());
        assertNotNull(userPO.getPassword());
    }
}
