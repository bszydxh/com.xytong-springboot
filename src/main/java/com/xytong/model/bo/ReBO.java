package com.xytong.model.bo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

 
@EqualsAndHashCode(callSuper = true)
@Data
public class ReBO extends BbsBO implements Serializable {
    private String price;

}
