package com.xytong.model.dto.captcha;

import lombok.Data;
/**
 * @author bszydxh
 */
@Data
public class CaptchaSendRequestDTO {
    Long timestamp;
    String email;
}
