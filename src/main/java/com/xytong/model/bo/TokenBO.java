package com.xytong.model.bo;

import lombok.Data;

@Data
public class TokenBO {
    String username;
    String password;
    long timestamp;
}
