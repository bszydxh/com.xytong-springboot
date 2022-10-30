package com.xytong.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName captcha
 */
@TableName("captcha")
@Data
public class CaptchaPO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String email;

    /**
     *
     */
    private Date timestamp;

    /**
     *
     */
    private String code;

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
        CaptchaPO other = (CaptchaPO) that;
        return (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", username=").append(email);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", code=").append(code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}