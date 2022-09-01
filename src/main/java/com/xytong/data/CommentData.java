package com.xytong.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentData extends CardData implements Serializable {
    Integer floor = -1;
    Integer likes = 0;

    @Override
    @Deprecated
    public String getTitle() {
        return "";
    }
    @Override
    @Deprecated
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
