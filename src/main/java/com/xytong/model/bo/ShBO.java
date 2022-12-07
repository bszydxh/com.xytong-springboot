package com.xytong.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author bszydxh
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ShBO extends BbsBO implements Serializable {
    public static final String SH_TABLE_NAME = "sh";
    private String price;
    @JsonProperty("image_url")
    private String imageUrl;
}
