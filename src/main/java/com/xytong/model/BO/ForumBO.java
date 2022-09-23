package com.xytong.model.BO;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumBO extends CardBO implements Serializable {
    private Integer likes = 0;
    private Integer comments = 0;
    private Integer forwarding = 0;
    ////////////////////////////////////////////////////////
}
