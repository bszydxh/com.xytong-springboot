package com.xytong.model.DTO;

import lombok.Data;

@Data
public class UserRequestDTO {
    String mode;
    String token;
    String username;
    String timestamp;
}
