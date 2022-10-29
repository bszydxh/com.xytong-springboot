package com.xytong.model.dto.access;

import lombok.Data;


/**
 * @author bszydxh
 */
@Data
public class AccessResponseDTO {
    String mode;
    String username;
    String token;
    Long timestamp;
}
