package com.xytong.model.dto.access;

import lombok.Data;
/**
 * @author bszydxh
 */
@Data
public class AccessSignupResponseDTO {
    Long timestamp;
    String mode;
    String token;
}
