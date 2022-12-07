package com.xytong.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author auto_generator
 * @TableName comment
 */
@TableName("comment")
@Data
public class CommentPO implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long cardFkey;

    private Object cardFtable;

    private Long userFkey;

    private Long replyFkey;

    private String text;

    private Object visibility;
    private Integer likes;

    private Integer floor;
    private Date timestamp;
    private static final long serialVersionUID = 1L;

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
        CommentPO other = (CommentPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCardFkey() == null ? other.getCardFkey() == null : this.getCardFkey().equals(other.getCardFkey()))
                && (this.getUserFkey() == null ? other.getUserFkey() == null : this.getUserFkey().equals(other.getUserFkey()))
                && (this.getReplyFkey() == null ? other.getReplyFkey() == null : this.getReplyFkey().equals(other.getReplyFkey()))
                && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
                && (this.getVisibility() == null ? other.getVisibility() == null : this.getVisibility().equals(other.getVisibility()))
                && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCardFkey() == null) ? 0 : getCardFkey().hashCode());
        result = prime * result + ((getUserFkey() == null) ? 0 : getUserFkey().hashCode());
        result = prime * result + ((getReplyFkey() == null) ? 0 : getReplyFkey().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getVisibility() == null) ? 0 : getVisibility().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cardFkey=").append(cardFkey);
        sb.append(", userFkey=").append(userFkey);
        sb.append(", replyFkey=").append(replyFkey);
        sb.append(", text=").append(text);
        sb.append(", visibility=").append(visibility);
        sb.append(", likes=").append(likes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}