package com.xytong.model.bo;


import com.xytong.model.po.RePO;
import com.xytong.model.po.UserPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReBO extends CardBO implements Serializable {
    private String price;

    public static ReBO init(RePO rePO, UserPO userPO) {
        if (userPO == null || rePO == null) {
            return null;
        }
        ReBO reBO = new ReBO();
        reBO.setId(rePO.getId());
        reBO.setTimestamp(rePO.getTimestamp().getTime());
        reBO.setUserName(userPO.getName());
        reBO.setUserAvatarUrl(userPO.getAvatar());
        reBO.setTitle(rePO.getTitle());
        reBO.setText(rePO.getText());
        reBO.setPrice(rePO.getPrice().toString());
        return reBO;
    }
}
