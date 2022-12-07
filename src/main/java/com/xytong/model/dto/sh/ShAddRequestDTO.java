package com.xytong.model.dto.sh;

import lombok.Data;
/**
 * @author bszydxh
 */
@Data
public class ShAddRequestDTO {
    String module;
    String token;
    String timestamp;
    String username;
    String title;
    String text;
    String price;
}
