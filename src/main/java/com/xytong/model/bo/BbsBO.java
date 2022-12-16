package com.xytong.model.bo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author bszydxh
 * 数据传输层用
 */

@Data
public class BbsBO implements Serializable {
    
    private Long cid;//卡片消息id
    @JsonProperty(value = "user_name")
    private String userName;
    @JsonProperty(value = "user_avatar")
    private String userAvatarUrl;
    private String title;
    private String text;
    private Long timestamp;
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
