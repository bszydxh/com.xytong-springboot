package com.xytong.model.dto.sh;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.bo.ShBO;
import com.xytong.model.dto.BbsGetDTO;
import lombok.Data;

import java.util.List;


@Data
public class ShGetResponseDTO implements BbsGetDTO {
    String mode;
    @JsonProperty(value = "num_start")
    Integer numStart;
    @JsonProperty(value = "need_num")
    Integer needNum;
    @JsonProperty(value = "num_end")
    Integer numEnd;
    Long timestamp;
    @JsonProperty(value = "sh_data")
    List<ShBO> data;

    @Override
    public List getData() {
        return data;
    }

    @Override
    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
