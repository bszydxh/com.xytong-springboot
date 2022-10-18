package com.xytong.model.dto.sh;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.bo.ShBO;
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
    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
