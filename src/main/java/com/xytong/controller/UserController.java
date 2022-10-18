package com.xytong.controller;


import com.xytong.model.bo.UserBO;
import com.xytong.model.dto.user.UserPostDTO;
import com.xytong.model.dto.user.UserRequestDTO;
import com.xytong.model.po.UserPO;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
@Slf4j
@RestController
public class UserController {

    final UserService userService;
    final AccessService accessService;

    public UserController(UserService userService, AccessService accessService) {
        this.userService = userService;
        this.accessService = accessService;
    }

    @RequestMapping(value = "/user", produces = "application/json")
    @ResponseBody
    public UserPostDTO getUser(@RequestBody UserRequestDTO userRequestDTO) {//还没测试
        UserPostDTO userPostDTO = new UserPostDTO();
        String token = userRequestDTO.getToken();
        String username = userRequestDTO.getUsername();
        boolean pass = false;
        UserPO userPO = userService.findUserByName(username);
        if (userPO == null) {
            userPostDTO.setMode("user error");
            return userPostDTO;
        }
        UserBO userBO = UserBO.init(userPO);
        if (Objects.equals(token, "") || token == null) {
            userPostDTO.setMode("token error");
        }
        if (accessService.tokenChecker(token)) {
            String token_username = accessService.tokenParser(token).getUsername();
            UserPO token_userPO = userService.findUserByName(token_username);
            log.info(token_userPO.toString());
            if (Objects.equals(token_username, username)) {
                pass = true;
            } else if (token_userPO.getIsAdmin().equals("Y")) {
                pass = true;
            } else {
                userPostDTO.setMode("access error");
            }
        } else {
            userPostDTO.setMode("token error");
        }

        if (pass) {
            userPostDTO.setAvatar(userBO.getUserAvatar());
            userPostDTO.setBirthday_timestamp(userBO.getBirthday());
            userPostDTO.setEmail(userBO.getEmail());
            userPostDTO.setGender(userBO.getGender());
            userPostDTO.setMode("");
            userPostDTO.setPhone(userBO.getPhone());
            userPostDTO.setSignature(userBO.getSignature());
            userPostDTO.setUsername(userBO.getName());
        } else {
            userPostDTO.setSignature(userBO.getSignature());
            userPostDTO.setUsername(userBO.getName());
            userPostDTO.setGender(userBO.getGender());
            userPostDTO.setAvatar(userBO.getUserAvatar());
        }

        return userPostDTO;
    }
}
