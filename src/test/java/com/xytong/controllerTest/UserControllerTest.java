package com.xytong.controllerTest;

import com.xytong.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;
}
