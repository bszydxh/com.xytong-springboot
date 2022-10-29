package com.xytong.model.dto.re;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.bo.ReBO;
import com.xytong.model.dto.BbsGetDTO;
import lombok.Data;

import java.util.List;

/**
 * @author bszydxh
 */

@Data
public class ReGetResponseDTO implements BbsGetDTO {
    String mode;
    @JsonProperty(value = "num_start")
    Integer numStart;
    @JsonProperty(value = "need_num")
    Integer needNum;
    @JsonProperty(value = "num_end")
    Integer numEnd;
    Long timestamp;
    @JsonProperty(value = "re_data")
    List<ReBO> data;

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
