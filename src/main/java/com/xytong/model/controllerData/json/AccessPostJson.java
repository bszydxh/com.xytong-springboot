package com.xytong.model.controllerData.json;

import lombok.Data;


@Data
public class AccessPostJson {
    String username;
    String token;
    long timestamp;
    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
