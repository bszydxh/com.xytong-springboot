package com.xytong.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bszydxh
 */
 
@Data
public class UserBO implements Serializable {
    private Long id;
    private Boolean admin;
    private String name;
    private String phone;
    private String gender;
    private Long birthday;
    private String email;
    private String password;
    private String userAvatar;
    private String signature;

}
