package com.xytong.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author bszydxh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentBO extends BbsBO implements Serializable {


    Integer floor;
    @JsonIgnore
    String module;
    Integer likes;

    @Override
    @Deprecated
    @JsonIgnore
    public String getTitle() {
        return "";
    }

    @Override
    @Deprecated
    @JsonIgnore
    public void setTitle(String title) {
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getLikes() {
        return likes;
    }
}
