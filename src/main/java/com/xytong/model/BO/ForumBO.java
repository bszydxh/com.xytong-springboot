package com.xytong.model.BO;


import com.xytong.model.PO.ForumPO;
import com.xytong.model.PO.UserPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumBO extends CardBO implements Serializable {
    private Integer likes = 0;
    private Integer comments = 0;
    private Integer forwarding = 0;

    public ForumBO(ForumPO forumPO, UserPO userPO) {
        if (forumPO == null || userPO == null) {
            return;
        }
        this.setId(forumPO.getId());
        this.setTimestamp(forumPO.getTimestamp().getTime());
        this.setUserName(userPO.getName());
        this.setUserAvatarUrl(userPO.getAvatar());
        this.setTitle(forumPO.getTitle());
        this.setText(forumPO.getText());
        this.setForwarding(forumPO.getForwarding());
        this.setComments(forumPO.getComments());
        this.setLikes(forumPO.getLikes());
    }


    ////////////////////////////////////////////////////////
}
