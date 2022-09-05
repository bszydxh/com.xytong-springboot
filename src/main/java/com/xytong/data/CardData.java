package com.xytong.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
@Data
public class CardData implements Serializable {
    @Getter(AccessLevel.NONE)
    @JsonIgnore
    private int id = -1;//主键
    @JsonIgnore
    private String uid = "null";//用户id，TODO
    @JsonIgnore
    private String cid = "null" ;//卡片消息id，TODO
    @JsonProperty(value = "user_name")
    private String userName = "null";
    @JsonProperty(value = "user_avatar")
    private String userAvatarUrl = "";
    private String title = "(没有标题)";
    private String text = "";
    private Long timestamp = 0L;
    ////////////////////////////////////////////////////////

    public String getText() {
        if (text == null) {
            return null;
        } else {
            return text.trim();
        }
    }

    public String getTitle() {
        if (title == null) {
            return null;
        } else {
            return title.trim();
        }
    }


    public String getUserName() {
        if (userName == null) {
            return null;
        } else {
            return userName.trim();
        }
    }

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }

}
