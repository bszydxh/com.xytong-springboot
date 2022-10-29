package com.xytong.service;

import com.xytong.model.bo.UserBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void checkUserTest() {
        assertFalse(userService.checkUser(null, ""));
        assertFalse(userService.checkUser(null, null));
        assertFalse(userService.checkUser("", ""));
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
        UserBO userBO = userService.findUserByName("bszydxh");
        log.info(userBO.toString());
        assertEquals("bszydxh", userBO.getName());
        UserBO userBO2 = userService.findUserByName("xzx");
        log.info(userBO.toString());
        assertEquals("xzx", userBO2.getName());
        assertNull(userService.findUserByName(null));
        assertNotNull(userBO2.getPassword());
        assertNotNull(userBO.getPassword());
    }

    @Test
    public void findUserByIdTest() {
        UserBO userBO = userService.findUserById(1L);
        log.info(userBO.toString());
        assertEquals("bszydxh", userBO.getName());
        UserBO userBO2 = userService.findUserById(2L);
        log.info(userBO.toString());
        assertEquals("xzx", userBO2.getName());
        assertNotNull(userBO2.getPassword());
        assertNotNull(userBO.getPassword());
    }
}
