package com.xytong.model.bo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author bszydxh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReBO extends BbsBO implements Serializable {
    public static final String RE_TABLE_NAME = "re";
    private String price;

}
