package com.xytong.model.dto.user;

import lombok.Data;

/**
 * @author bszydxh
 */
@Data
public class UserGetResponseDTO {
    String mode;
    String username;
    String avatar;
    String signature;
    String gender;
    Long birthday_timestamp;
    String phone;
    String email;
}
