package com.xytong.model.dto;

import lombok.Data;


@Data
public class AccessPostDTO {
    String mode;
    String username;
    String token;
    long timestamp;
}
