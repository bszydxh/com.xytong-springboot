package com.xytong;

import com.xytong.model.BO.UserBO;
import com.xytong.model.PO.UserPO;
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
    public void checkUserTest() throws Exception {

    }

    @Test
    public void findUserByNameTest() throws Exception {
        UserPO userPO = userService.findUserByName("bszydxh");
        assertEquals("bszydxh", userPO.getName());
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info(userPO.getPassword());
        assertNotNull(userPO.getPassword());
    }

}
