package com.xytong.model.dto.access;

import lombok.Data;

@Data
public class AccessRequestDTO {
    String mode;
    String username;
    String password;
    String token;
    long timestamp;
}
