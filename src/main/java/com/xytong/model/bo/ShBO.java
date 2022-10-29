package com.xytong.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

 
@EqualsAndHashCode(callSuper = true)
@Data
public class ShBO extends BbsBO implements Serializable {
    private String price;
    @JsonProperty("image_url")
    private String imageUrl;
}
