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

    public String getDate() {
        long startTime = timestamp * 1000;
        long endTime = System.currentTimeMillis();  //获取毫秒数
        long timeDifference = endTime - startTime;
        long second = timeDifference / 1000;    //计算秒
        if (startTime == 0) {
            return "null";
        }
        if (second < 60) {
            return second + " 秒前";
        } else {
            long minute = second / 60;
            if (minute < 60) {
                return minute + "分钟前";
            } else {
                long hour = minute / 60;
                if (hour < 24) {
                    return hour + "小时前";
                } else {
                    long day = hour / 24;
                    if (day < 30) {
                        return day + "天前";
                    } else {
                        long month = day / 30;
                        if (month < 12) {
                            return month + "月前";
                        } else {
                            long year = day / 365;
                            return year + "年前";
                        }
                    }

                }
            }
        }

    }
}
