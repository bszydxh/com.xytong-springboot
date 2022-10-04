package com.xytong.model.bo;

import com.xytong.model.po.UserPO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserBO implements Serializable {
    private String name;
    private String phone;
    private String gender;
    private Long birthday;
    private String email;
    private String password;
    private String userAvatar;
    private String signature;

    public static UserBO init(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
       userBO.setName(userPO.getName());
       userBO.setPhone(userPO.getPhone());
       userBO.setGender((String) userPO.getGender());
       userBO.setBirthday(userPO.getBirthdayTimestamp().getTime());
       userBO.setEmail(userPO.getEmail());
       userBO.setPassword(userPO.getPassword());
       userBO.setUserAvatar(userPO.getAvatar());
       userBO.setSignature(userPO.getSignature());
        return userBO;
    }

}
