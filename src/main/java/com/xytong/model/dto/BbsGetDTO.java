package com.xytong.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * @author bszydxh
 */
public interface BbsGetDTO {
    void setData(List data);

    List getData();

    String getTimestamp();

    String getMode();

    Integer getNumStart();

    Integer getNeedNum();

    Integer getNumEnd();

    void setMode(String mode);

    @JsonProperty("num_start")
    void setNumStart(Integer numStart);

    @JsonProperty("need_num")
    void setNeedNum(Integer needNum);

    @JsonProperty("num_end")
    void setNumEnd(Integer numEnd);

    void setTimestamp(Long timestamp);
}
