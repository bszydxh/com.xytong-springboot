package com.xytong.model.DTO;

import lombok.Data;

@Data
public class UserPostDTO {
    String mode;
    String username;
    String avatar;
    String signature;
    String gender;
    Long birthday_timestamp;
    String phone;
    String email;
}
