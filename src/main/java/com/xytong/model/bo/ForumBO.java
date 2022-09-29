package com.xytong.model.bo;


import com.xytong.model.po.ForumPO;
import com.xytong.model.po.UserPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumBO extends CardBO implements Serializable {
    private Integer likes;
    private Integer comments;
    private Integer forwarding;

    public static ForumBO init(ForumPO forumPO, UserPO userPO) {
        if (forumPO == null || userPO == null) {
            return null;
        }
        ForumBO forumBO = new ForumBO();
        forumBO.setId(forumPO.getId());
        forumBO.setTimestamp(forumPO.getTimestamp().getTime());
        forumBO.setUserName(userPO.getName());
        forumBO.setUserAvatarUrl(userPO.getAvatar());
        forumBO.setTitle(forumPO.getTitle());
        forumBO.setText(forumPO.getText());
        forumBO.setForwarding(forumPO.getForwarding());
        forumBO.setComments(forumPO.getComments());
        forumBO.setLikes(forumPO.getLikes());
        return forumBO;
    }


    ////////////////////////////////////////////////////////
}
