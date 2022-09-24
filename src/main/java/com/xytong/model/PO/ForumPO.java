package com.xytong.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xytong.model.BO.ForumBO;

import java.io.Serializable;
import java.util.Date;

@TableName("forum")
public class ForumPO implements Serializable {//面向数据库
    private Integer id;
    private Integer userFkey;
    private Integer likes;
    private Integer comments;
    private Integer forwarding;
    private String title;
    private String text;
    private Date timestamp;
    private static final long serialVersionUID = 1L;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserFkey() {
        return userFkey;
    }
    public void setUserFkey(Integer userFkey) {
        this.userFkey = userFkey;
    }
    public Integer getLikes() {
        return likes;
    }
    public void setLikes(Integer likes) {
        this.likes = likes;
    }
    public Integer getComments() {
        return comments;
    }
    public void setComments(Integer comments) {
        this.comments = comments;
    }
    public Integer getForwarding() {
        return forwarding;
    }
    public void setForwarding(Integer forwarding) {
        this.forwarding = forwarding;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ForumPO other = (ForumPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserFkey() == null ? other.getUserFkey() == null : this.getUserFkey().equals(other.getUserFkey()))
            && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()))
            && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()))
            && (this.getForwarding() == null ? other.getForwarding() == null : this.getForwarding().equals(other.getForwarding()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserFkey() == null) ? 0 : getUserFkey().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        result = prime * result + ((getForwarding() == null) ? 0 : getForwarding().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userFkey=").append(userFkey);
        sb.append(", likes=").append(likes);
        sb.append(", comments=").append(comments);
        sb.append(", forwarding=").append(forwarding);
        sb.append(", title=").append(title);
        sb.append(", text=").append(text);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}