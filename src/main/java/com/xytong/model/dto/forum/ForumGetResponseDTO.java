package com.xytong.model.dto.forum;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.dto.BbsGetDTO;

import java.util.List;


public class ForumGetResponseDTO implements BbsGetDTO {
    String mode;
    @JsonProperty(value = "num_start")
    Integer numStart;
    @JsonProperty(value = "need_num")
    Integer needNum;
    @JsonProperty(value = "num_end")
    Integer numEnd;
    Long timestamp;
    @JsonProperty(value = "forum_data")
    List<ForumBO> data;

    @Override
    public void setData(List data) {
        this.data = data;
    }

    public String getMode() {
        return this.mode;
    }

    public Integer getNumStart() {
        return this.numStart;
    }

    public Integer getNeedNum() {
        return this.needNum;
    }

    public Integer getNumEnd() {
        return this.numEnd;
    }

    public String getTimestamp() {
        return String.valueOf(this.timestamp);
    }

    public List<ForumBO> getData() {
        return this.data;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("num_start")
    public void setNumStart(Integer numStart) {
        this.numStart = numStart;
    }

    @JsonProperty("need_num")
    public void setNeedNum(Integer needNum) {
        this.needNum = needNum;
    }

    @JsonProperty("num_end")
    public void setNumEnd(Integer numEnd) {
        this.numEnd = numEnd;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ForumGetResponseDTO)) return false;
        final ForumGetResponseDTO other = (ForumGetResponseDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$mode = this.getMode();
        final Object other$mode = other.getMode();
        if (this$mode == null ? other$mode != null : !this$mode.equals(other$mode)) return false;
        final Object this$numStart = this.getNumStart();
        final Object other$numStart = other.getNumStart();
        if (this$numStart == null ? other$numStart != null : !this$numStart.equals(other$numStart)) return false;
        final Object this$needNum = this.getNeedNum();
        final Object other$needNum = other.getNeedNum();
        if (this$needNum == null ? other$needNum != null : !this$needNum.equals(other$needNum)) return false;
        final Object this$numEnd = this.getNumEnd();
        final Object other$numEnd = other.getNumEnd();
        if (this$numEnd == null ? other$numEnd != null : !this$numEnd.equals(other$numEnd)) return false;
        final Object this$timestamp = this.getTimestamp();
        final Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ForumGetResponseDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $mode = this.getMode();
        result = result * PRIME + ($mode == null ? 43 : $mode.hashCode());
        final Object $numStart = this.getNumStart();
        result = result * PRIME + ($numStart == null ? 43 : $numStart.hashCode());
        final Object $needNum = this.getNeedNum();
        result = result * PRIME + ($needNum == null ? 43 : $needNum.hashCode());
        final Object $numEnd = this.getNumEnd();
        result = result * PRIME + ($numEnd == null ? 43 : $numEnd.hashCode());
        final Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "ForumGetResponseDTO(mode=" + this.getMode() + ", numStart=" + this.getNumStart() + ", needNum=" + this.getNeedNum() + ", numEnd=" + this.getNumEnd() + ", timestamp=" + this.getTimestamp() + ", data=" + this.getData() + ")";
    }
}
