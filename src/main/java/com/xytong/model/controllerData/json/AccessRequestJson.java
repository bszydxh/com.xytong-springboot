package com.xytong.model.controllerData.json;

import lombok.Data;

@Data
public class AccessRequestJson {
    String username;
    String password;
    String token;
    long timestamp;
    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
