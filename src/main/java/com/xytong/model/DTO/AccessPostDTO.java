package com.xytong.model.DTO;

import lombok.Data;


@Data
public class AccessPostDTO {
    String mode;
    String username;
    String token;
    long timestamp;
}
