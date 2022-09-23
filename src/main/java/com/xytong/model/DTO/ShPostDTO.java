package com.xytong.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.BO.ShBO;
import lombok.Data;

import java.util.List;

@Data
public class ShPostDTO {
    String mode;
    @JsonProperty(value = "num_start")
    int numStart;
    @JsonProperty(value = "need_num")
    int needNum;
    @JsonProperty(value = "num_end")
    int numEnd;
    long timestamp;
    @JsonProperty(value = "sh_data")
    List<ShBO> shData;

    public void setShData(List<ShBO> shData) {
        this.shData = shData;
    }

    public List<ShBO> getShData() {
        return shData;
    }

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
