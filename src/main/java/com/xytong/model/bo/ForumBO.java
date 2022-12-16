package com.xytong.model.bo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author bszydxh
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ForumBO extends BbsBO implements Serializable {
    public static final String FORUM_TABLE_NAME = "forum";
    private Integer likes;
    private Integer comments;
    private Integer forwarding;
}
