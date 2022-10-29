package com.xytong.model.bo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

 
/**
 * @author bszydxh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ForumBO extends BbsBO implements Serializable {
    private Integer likes;
    private Integer comments;
    private Integer forwarding;
}
