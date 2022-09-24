package com.xytong.model.BO;


import com.xytong.model.PO.RePO;
import com.xytong.model.PO.ShPO;
import com.xytong.model.PO.UserPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReBO extends CardBO implements Serializable {
    private String price = "null";

    public ReBO(RePO rePO, UserPO userPO) {
        if (userPO == null || rePO == null) {
            return;
        }
        this.setId(rePO.getId());
        this.setTimestamp(rePO.getTimestamp().getTime());
        this.setUserName(userPO.getName());
        this.setUserAvatarUrl(userPO.getAvatar());
        this.setTitle(rePO.getTitle());
        this.setText(rePO.getText());
        this.setPrice(rePO.getPrice().toString());
    }
}
