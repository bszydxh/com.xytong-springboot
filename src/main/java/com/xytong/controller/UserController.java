package com.xytong.controller;


import com.xytong.model.bo.UserBO;
import com.xytong.model.dto.user.UserGetRequestDTO;
import com.xytong.model.dto.user.UserGetResponseDTO;
import com.xytong.service.AccessService;
import com.xytong.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bszydxh
 */
@RestController
public class UserController {

    final UserService userService;

    final AccessService accessService;

    public UserController(UserService userService, AccessService accessService) {
        this.userService = userService;
        this.accessService = accessService;
    }
    public static final String USER_MODULE_NAME = "user";
    @RequestMapping(value = "/"+USER_MODULE_NAME+"/v1/get", produces = "application/json")

    @ResponseBody
    public UserGetResponseDTO getUser(@RequestBody UserGetRequestDTO userGetRequestDTO) {
        UserGetResponseDTO userGetResponseDTO = new UserGetResponseDTO();
        String token = userGetRequestDTO.getToken();
        String username = userGetRequestDTO.getUsername();
        UserBO userBO = userService.findUserByName(username);
        if (userBO == null) {
            userGetResponseDTO.setMode("user error");
            return userGetResponseDTO;
        }
        if (accessService.tokenCheckerWithUsername(token, userGetRequestDTO.getUsername())) {
            userGetResponseDTO.setAvatar(userBO.getUserAvatar());
            userGetResponseDTO.setBirthday_timestamp(userBO.getBirthday());
            userGetResponseDTO.setEmail(userBO.getEmail());
            userGetResponseDTO.setGender(userBO.getGender());
            userGetResponseDTO.setMode("");
            userGetResponseDTO.setPhone(userBO.getPhone());
            userGetResponseDTO.setSignature(userBO.getSignature());
            userGetResponseDTO.setUsername(userBO.getName());
        } else {
            userGetResponseDTO.setSignature(userBO.getSignature());
            userGetResponseDTO.setUsername(userBO.getName());
            userGetResponseDTO.setGender(userBO.getGender());
            userGetResponseDTO.setAvatar(userBO.getUserAvatar());
        }
        return userGetResponseDTO;
    }
}
