package com.xytong.model.dto.comment;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author bszydxh
 */
@Data
public class CommentAddRequestDTO {
    String module;
    String token;
    String cid;
    String timestamp;
    String username;
    String text;
}
