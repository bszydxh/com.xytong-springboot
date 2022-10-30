package com.xytong.model.dto.user;

import lombok.Data;

 
@Data
public class UserGetRequestDTO {
    String mode;
    String token;
    String username;
    String timestamp;
}
