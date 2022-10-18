package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.ForumMapper;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.po.ForumPO;
import com.xytong.model.po.UserPO;
import com.xytong.service.ForumService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【forum】的数据库操作Service实现
 * @createDate 2022-09-03 01:13:25
 */
@Slf4j
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, ForumPO>
        implements ForumService {
    final UserService userService;

    public ForumServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<ForumBO> getForumList(String mode, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<ForumBO> forumList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                List<ForumPO> forumPOList = list();
                for (ForumPO forumPO : forumPOList) {
                    Long uid = forumPO.getUserFkey();
                    forumList.add(ForumBO.init(forumPO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return forumList;
    }

    @Override
    public Boolean addForum(ForumBO forumBO) {
        if (forumBO == null) {
            return false;
        }
        try {
            ForumPO forumPO = new ForumPO();
            UserPO userPO = userService.findUserByName(forumBO.getUserName());
            if (userPO == null) {
                log.error("not a valid date");
                return false;
            }
            forumPO.setUserFkey(userPO.getId());
            forumPO.setLikes(forumBO.getLikes());
            forumPO.setComments(forumBO.getComments());
            forumPO.setForwarding(forumBO.getForwarding());
            forumPO.setTitle(forumBO.getTitle());
            forumPO.setText(forumBO.getText());
            Date date = null;
            try {
                date =new Date(Long.parseLong(forumBO.getTimestamp()));
                forumPO.setTimestamp(date);
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("not a valid date");
            }
            save(forumPO);
        } catch (Exception e) {
            log.error("save error");
            return false;

        }
        return true;
    }
}

