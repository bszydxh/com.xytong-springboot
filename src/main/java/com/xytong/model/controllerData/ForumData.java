package com.xytong.model.controllerData;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumData extends CardData implements Serializable {
    private Integer likes = 0;
    private Integer comments = 0;
    private Integer forwarding = 0;
    ////////////////////////////////////////////////////////
}
