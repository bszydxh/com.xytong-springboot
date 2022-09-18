package com.xytong.model.controllerData.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.controllerData.ReData;
import lombok.Data;

import java.util.List;

@Data
public class RePostJson {
    String mode;
    @JsonProperty(value = "num_start")
    int numStart;
    @JsonProperty(value = "need_num")
    int needNum;
    @JsonProperty(value = "num_end")
    int numEnd;
    long timestamp;
    @JsonProperty(value = "re_data")
    List<ReData> reData;

    public void setReData(List<ReData> reData) {
        this.reData = reData;
    }

    public List<ReData> getReData() {
        return reData;
    }

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
