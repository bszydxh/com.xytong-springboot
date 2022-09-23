package com.xytong.controller;


import com.xytong.model.DTO.UserPostDTO;
import com.xytong.model.DTO.UserRequestDTO;
import com.xytong.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", produces = "application/json")
    public UserPostDTO userRequestMapping(@RequestBody UserRequestDTO userRequestDTO) {
        UserPostDTO userPostDTO = new UserPostDTO();
        userRequestDTO.getUsername();
        return userPostDTO;
    }
}
