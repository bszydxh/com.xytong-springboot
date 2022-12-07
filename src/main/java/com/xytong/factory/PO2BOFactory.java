package com.xytong.factory;

import com.xytong.model.bo.*;
import com.xytong.model.po.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 工厂类进行解耦,本应该使用BeanUtils解耦的，陈年老屎山
 * 主要负责对po进行装箱（转bo）
 * @author bszydxh
 */
@Slf4j
public class PO2BOFactory {

    public static ShBO getShBO(ShPO shPO, UserBO userBO) {
        if (userBO == null || shPO == null) {
            return null;
        }
        ShBO shBO = new ShBO();
        shBO.setCid(shPO.getId());
        shBO.setTimestamp(shPO.getTimestamp().getTime());
        shBO.setUserName(userBO.getName());
        shBO.setUserAvatarUrl(userBO.getUserAvatar());
        shBO.setTitle(shPO.getTitle());
        shBO.setText(shPO.getText());
        shBO.setImageUrl(shPO.getImageUrl());
        shBO.setPrice(shPO.getPrice().toString());
        return shBO;
    }

    public static ShBO getShBO(ShPO shPO, UserPO userPO) {
        return getShBO(shPO, getUserBO(userPO));
    }

    public static ForumBO getForumBO(ForumPO forumPO, UserBO userBO) {
        if (forumPO == null || userBO == null) {
            return null;
        }
        ForumBO forumBO = new ForumBO();
        forumBO.setCid(forumPO.getId());
        forumBO.setTimestamp(forumPO.getTimestamp().getTime());
        forumBO.setUserName(userBO.getName());
        forumBO.setUserAvatarUrl(userBO.getUserAvatar());
        forumBO.setTitle(forumPO.getTitle());
        forumBO.setText(forumPO.getText());
        forumBO.setForwarding(forumPO.getForwarding());
        forumBO.setComments(forumPO.getComments());
        forumBO.setLikes(forumPO.getLikes());
        return forumBO;
    }

    public static ForumBO getForumBO(ForumPO forumPO, UserPO userPO) {
        return getForumBO(forumPO, getUserBO(userPO));
    }

    public static ReBO getReBO(RePO rePO, UserBO userBO) {
        if (userBO == null || rePO == null) {
            return null;
        }
        ReBO reBO = new ReBO();
        reBO.setCid(rePO.getId());
        reBO.setTimestamp(rePO.getTimestamp().getTime());
        reBO.setUserName(userBO.getName());
        reBO.setUserAvatarUrl(userBO.getUserAvatar());
        reBO.setTitle(rePO.getTitle());
        reBO.setText(rePO.getText());
        reBO.setPrice(rePO.getPrice().toString());
        return reBO;
    }

    public static ReBO getReBO(RePO forumPO, UserPO userPO) {
        return getReBO(forumPO, getUserBO(userPO));
    }

    public static UserBO getUserBO(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        userBO.setId(userPO.getId());
        userBO.setAdmin("Y".equals(userPO.getIsAdmin()));
        userBO.setName(userPO.getName());
        userBO.setPhone(userPO.getPhone());
        userBO.setGender((String) userPO.getGender());
        //发生了空指针！多重调用api务必小心
        userBO.setBirthday(userPO.getBirthdayTimestamp() == null ? 1 : userPO.getBirthdayTimestamp().getTime());
        userBO.setEmail(userPO.getEmail());
        userBO.setPassword(userPO.getPassword());
        userBO.setUserAvatar(userPO.getAvatar());
        userBO.setSignature(userPO.getSignature());
        return userBO;
    }

    public static CommentBO getCommentBO(CommentPO commentPO, UserBO userBO) {
        if (userBO == null || commentPO == null) {
            return null;
        }
        if (!"visible".equals(commentPO.getVisibility())) {
            return null;
        }
        CommentBO commentBO = new CommentBO();
        commentBO.setFloor(commentPO.getFloor());
        commentBO.setLikes(commentPO.getLikes());
        commentBO.setCid(commentPO.getId());
        commentBO.setUserName(userBO.getName());
        commentBO.setUserAvatarUrl(userBO.getUserAvatar());
        commentBO.setText(commentPO.getText());
        commentBO.setTimestamp(commentPO.getTimestamp().getTime());
        return commentBO;
    }
}
