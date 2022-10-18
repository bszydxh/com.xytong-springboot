package com.xytong.model.dto.re;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.bo.ReBO;
import lombok.Data;

import java.util.List;

@Data
public class RePostDTO {
    String mode;
    @JsonProperty(value = "num_start")
    int numStart;
    @JsonProperty(value = "need_num")
    int needNum;
    @JsonProperty(value = "num_end")
    int numEnd;
    long timestamp;
    @JsonProperty(value = "re_data")
    List<ReBO> reData;

    public void setReData(List<ReBO> reData) {
        this.reData = reData;
    }

    public List<ReBO> getReData() {
        return reData;
    }

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
