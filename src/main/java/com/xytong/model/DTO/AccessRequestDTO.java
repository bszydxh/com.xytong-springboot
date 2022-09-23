package com.xytong.model.DTO;

import lombok.Data;

@Data
public class AccessRequestDTO {
    String mode;
    String username;
    String password;
    String token;
    long timestamp;
}
