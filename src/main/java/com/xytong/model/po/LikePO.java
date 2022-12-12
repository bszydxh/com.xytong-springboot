package com.xytong.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("like")
public class LikePO {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long cardFkey;

    private Object cardFtable;

    private Long userFkey;

    private Date timestamp;

    private static final long serialVersionUID = 1L;

    public LikePO() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getCardFkey() {
        return this.cardFkey;
    }

    public Object getCardFtable() {
        return this.cardFtable;
    }

    public Long getUserFkey() {
        return this.userFkey;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardFkey(Long cardFkey) {
        this.cardFkey = cardFkey;
    }

    public void setCardFtable(Object cardFtable) {
        this.cardFtable = cardFtable;
    }

    public void setUserFkey(Long userFkey) {
        this.userFkey = userFkey;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LikePO)) return false;
        final LikePO other = (LikePO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$cardFkey = this.getCardFkey();
        final Object other$cardFkey = other.getCardFkey();
        if (this$cardFkey == null ? other$cardFkey != null : !this$cardFkey.equals(other$cardFkey)) return false;
        final Object this$cardFtable = this.getCardFtable();
        final Object other$cardFtable = other.getCardFtable();
        if (this$cardFtable == null ? other$cardFtable != null : !this$cardFtable.equals(other$cardFtable))
            return false;
        final Object this$userFkey = this.getUserFkey();
        final Object other$userFkey = other.getUserFkey();
        if (this$userFkey == null ? other$userFkey != null : !this$userFkey.equals(other$userFkey)) return false;
        final Object this$timestamp = this.getTimestamp();
        final Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LikePO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $cardFkey = this.getCardFkey();
        result = result * PRIME + ($cardFkey == null ? 43 : $cardFkey.hashCode());
        final Object $cardFtable = this.getCardFtable();
        result = result * PRIME + ($cardFtable == null ? 43 : $cardFtable.hashCode());
        final Object $userFkey = this.getUserFkey();
        result = result * PRIME + ($userFkey == null ? 43 : $userFkey.hashCode());
        final Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        return result;
    }

    public String toString() {
        return "LikePO(id=" + this.getId() + ", cardFkey=" + this.getCardFkey() + ", cardFtable=" + this.getCardFtable() + ", userFkey=" + this.getUserFkey() + ", timestamp=" + this.getTimestamp() + ")";
    }
}
