package com.xytong.data.json;

import lombok.Data;

@Data
public class AccessRequestJson {
    String username;
    String password;
    long timestamp;
    public String getTimestamp() {
        return String.valueOf(timestamp);
    }

}
