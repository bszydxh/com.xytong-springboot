package com.xytong.data.json.domain;

import com.xytong.data.ReData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName run_errand
 */
public class Re implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userFkey;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String text;

    /**
     * 
     */
    private Date timestamp;

    /**
     * 
     */
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public Integer getUserFkey() {
        return userFkey;
    }

    /**
     * 
     */
    public void setUserFkey(Integer userFkey) {
        this.userFkey = userFkey;
    }

    /**
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     */
    public String getText() {
        return text;
    }

    /**
     * 
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * 
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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
        Re other = (Re) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserFkey() == null ? other.getUserFkey() == null : this.getUserFkey().equals(other.getUserFkey()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserFkey() == null) ? 0 : getUserFkey().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
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
        sb.append(", title=").append(title);
        sb.append(", text=").append(text);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public ReData toReDataWithUserData(User user) {
        ReData reData = new ReData();
        reData.setId(id);
        reData.setTimestamp(timestamp.getTime());
        if (user == null) {
            user = new User();
        }
        reData.setUserName(user.getName());
        reData.setUserAvatarUrl(user.getAvatar());
        reData.setTitle(title);
        reData.setText(text);
        reData.setPrice(price.toString());
        return reData;
    }
}