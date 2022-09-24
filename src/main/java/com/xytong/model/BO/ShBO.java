package com.xytong.model.BO;

import com.xytong.model.PO.ShPO;
import com.xytong.model.PO.UserPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class ShBO extends CardBO implements Serializable {
    private String price = "null";
    public ShBO (ShPO shPO, UserPO userPO) {
        if (userPO == null || shPO == null) {
            return;
        }
        this.setId(shPO.getId());
        this.setTimestamp(shPO.getTimestamp().getTime());
        this.setUserName(userPO.getName());
        this.setUserAvatarUrl(userPO.getAvatar());
        this.setTitle(shPO.getTitle());
        this.setText(shPO.getText());
        this.setPrice(shPO.getPrice().toString());
    }
}
