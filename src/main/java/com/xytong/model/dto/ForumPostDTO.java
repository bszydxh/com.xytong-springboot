package com.xytong.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xytong.model.bo.ForumBO;
import lombok.Data;

import java.util.List;

@Data
public class ForumPostDTO {
    String mode;
    @JsonProperty(value = "num_start")
    int numStart;
    @JsonProperty(value = "need_num")
    int needNum;
    @JsonProperty(value = "num_end")
    int numEnd;
    long timestamp;
    @JsonProperty(value = "forum_data")
    List<ForumBO> forumData;

    public void setForumData(List<ForumBO> forumData) {
        this.forumData = forumData;
    }

    public List<ForumBO> getForumData() {
        return forumData;
    }

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }
}
