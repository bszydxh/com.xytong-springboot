package com.xytong.controller;


import com.xytong.model.bo.UserBO;
import com.xytong.model.dto.user.UserResponseDTO;
import com.xytong.model.dto.user.UserRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bszydxh
 */
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
    public UserResponseDTO getUser(@RequestBody UserRequestDTO userRequestDTO) {//还没测试
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        String token = userRequestDTO.getToken();
        String username = userRequestDTO.getUsername();
        boolean pass = false;
        UserBO userBO = userService.findUserByName(username);
        if (userBO == null) {
            userResponseDTO.setMode("user error");
            return userResponseDTO;
        }
        if (accessService.tokenCheckerWithUsername(token, userRequestDTO.getUsername())) {
            userResponseDTO.setAvatar(userBO.getUserAvatar());
            userResponseDTO.setBirthday_timestamp(userBO.getBirthday());
            userResponseDTO.setEmail(userBO.getEmail());
            userResponseDTO.setGender(userBO.getGender());
            userResponseDTO.setMode("");
            userResponseDTO.setPhone(userBO.getPhone());
            userResponseDTO.setSignature(userBO.getSignature());
            userResponseDTO.setUsername(userBO.getName());
        } else {
            userResponseDTO.setSignature(userBO.getSignature());
            userResponseDTO.setUsername(userBO.getName());
            userResponseDTO.setGender(userBO.getGender());
            userResponseDTO.setAvatar(userBO.getUserAvatar());
        }

        return userResponseDTO;
    }
}
