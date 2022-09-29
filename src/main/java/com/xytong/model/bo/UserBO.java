package com.xytong.model.bo;

import com.xytong.model.po.UserPO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserBO implements Serializable {
    private String name;
    private String id;
    private String phoneNumber;
    private Integer sex;
    private Long birthday;
    private String email;
    private String password;
    private String userAvatarUrl;

    public static UserBO init(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        return userBO;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public Long getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

}
