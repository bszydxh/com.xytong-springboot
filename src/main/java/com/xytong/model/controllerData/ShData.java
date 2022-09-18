package com.xytong.model.controllerData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class ShData extends CardData implements Serializable {
    private String price = "null";

    ////////////////////////////////////////////////////////
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
