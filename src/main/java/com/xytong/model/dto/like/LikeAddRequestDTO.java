package com.xytong.model.dto.like;

import lombok.Data;

@Data
public class LikeAddRequestDTO {
    String module;
    String token;
    String username;
    String cid;
    Long timestamp;
}
