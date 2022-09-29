package com.xytong.model.bo;

import com.xytong.model.po.ShPO;
import com.xytong.model.po.UserPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShBO extends CardBO implements Serializable {
    private String price;

    public static ShBO init(ShPO shPO, UserPO userPO) {
        if (userPO == null || shPO == null) {
            return null;
        }
        ShBO shBO = new ShBO();
        shBO.setId(shPO.getId());
        shBO.setTimestamp(shPO.getTimestamp().getTime());
        shBO.setUserName(userPO.getName());
        shBO.setUserAvatarUrl(userPO.getAvatar());
        shBO.setTitle(shPO.getTitle());
        shBO.setText(shPO.getText());
        shBO.setPrice(shPO.getPrice().toString());
        return shBO;
    }
}
