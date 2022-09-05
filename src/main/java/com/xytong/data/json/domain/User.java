package com.xytong.data.json.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
public class User implements Serializable {
     
    private Integer id;

     
    private String name = "未知用户";

     
    private String phone ;

     
    private Date createTimestamp;

     
    private Object isAdmin;

     
    private String avatar = "";

     
    private Object gender;

     
    private Date birthdayTimestamp;

     
    private String email;

     
    private String password;

     
    private String token;

     
    private Date tokenPassTimestamp;

    private static final long serialVersionUID = 1L;

     
    public Integer getId() {
        return id;
    }

     
    public void setId(Integer id) {
        this.id = id;
    }

     
    public String getName() {
        return name;
    }

     
    public void setName(String name) {
        this.name = name;
    }

     
    public String getPhone() {
        return phone;
    }

     
    public void setPhone(String phone) {
        this.phone = phone;
    }

     
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

     
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

     
    public Object getIsAdmin() {
        return isAdmin;
    }

     
    public void setIsAdmin(Object isAdmin) {
        this.isAdmin = isAdmin;
    }

     
    public String getAvatar() {
        return avatar;
    }

     
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

     
    public Object getGender() {
        return gender;
    }

     
    public void setGender(Object gender) {
        this.gender = gender;
    }

     
    public Date getBirthdayTimestamp() {
        return birthdayTimestamp;
    }

     
    public void setBirthdayTimestamp(Date birthdayTimestamp) {
        this.birthdayTimestamp = birthdayTimestamp;
    }

     
    public String getEmail() {
        return email;
    }

     
    public void setEmail(String email) {
        this.email = email;
    }

     
    public String getPassword() {
        return password;
    }

     
    public void setPassword(String password) {
        this.password = password;
    }

     
    public String getToken() {
        return token;
    }

     
    public void setToken(String token) {
        this.token = token;
    }

     
    public Date getTokenPassTimestamp() {
        return tokenPassTimestamp;
    }

     
    public void setTokenPassTimestamp(Date tokenPassTimestamp) {
        this.tokenPassTimestamp = tokenPassTimestamp;
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCreateTimestamp() == null ? other.getCreateTimestamp() == null : this.getCreateTimestamp().equals(other.getCreateTimestamp()))
            && (this.getIsAdmin() == null ? other.getIsAdmin() == null : this.getIsAdmin().equals(other.getIsAdmin()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthdayTimestamp() == null ? other.getBirthdayTimestamp() == null : this.getBirthdayTimestamp().equals(other.getBirthdayTimestamp()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getTokenPassTimestamp() == null ? other.getTokenPassTimestamp() == null : this.getTokenPassTimestamp().equals(other.getTokenPassTimestamp()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCreateTimestamp() == null) ? 0 : getCreateTimestamp().hashCode());
        result = prime * result + ((getIsAdmin() == null) ? 0 : getIsAdmin().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthdayTimestamp() == null) ? 0 : getBirthdayTimestamp().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getTokenPassTimestamp() == null) ? 0 : getTokenPassTimestamp().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", avatar=").append(avatar);
        sb.append(", gender=").append(gender);
        sb.append(", birthdayTimestamp=").append(birthdayTimestamp);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", token=").append(token);
        sb.append(", tokenPassTimestamp=").append(tokenPassTimestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}