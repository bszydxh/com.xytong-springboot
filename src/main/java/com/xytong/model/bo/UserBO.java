package com.xytong.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bszydxh
 */
 
@Data
public class UserBO implements Serializable {
    public static final String GENDER_NORMAL = "unknown";
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_UNKNOWN = "unknown";
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
