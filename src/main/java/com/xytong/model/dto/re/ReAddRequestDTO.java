package com.xytong.model.dto.re;

import lombok.Data;
/**
 * @author bszydxh
 */
@Data
public class ReAddRequestDTO {
    String module;
    String token;
    String timestamp;
    String username;
    String title;
    String text;
    String price;
}
