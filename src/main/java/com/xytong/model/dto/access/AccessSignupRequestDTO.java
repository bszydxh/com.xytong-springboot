package com.xytong.model.dto.access;

import lombok.Data;
/**
 * @author bszydxh
 */
@Data
public class AccessSignupRequestDTO {
    Long timestamp;
    String username;
    String email;
    String password;
    String captchaCode;
}
