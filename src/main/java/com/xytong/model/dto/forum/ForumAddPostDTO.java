package com.xytong.model.dto.forum;

import lombok.Data;

@Data
public class ForumAddPostDTO {//TODO 需要加入token校验
    String token;
    String timestamp;
    String username;
}
