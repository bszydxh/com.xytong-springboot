package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.factory.PO2BOFactory;
import com.xytong.mapper.ForumMapper;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.ForumPO;
import com.xytong.service.ForumService;
import com.xytong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @NotNull
    @Override
    public List<ForumBO> getForumList(String mode, Long timestamp, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<ForumBO> forumList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                QueryWrapper<ForumPO> wrapper = new QueryWrapper<>();
                Date date = new Date(timestamp);
                //过滤新数据
                wrapper.le("timestamp", date);
                wrapper.last("ORDER BY `id` DESC LIMIT " + start + "," + (end - start + 1));
                List<ForumPO> forumPOList = list(wrapper);
                for (ForumPO forumPO : forumPOList) {
                    Long uid = forumPO.getUserFkey();
                    forumList.add(PO2BOFactory.getForumBO(forumPO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return forumList;
    }

    @Override
    public boolean addForum(ForumBO forumBO) {
        if (forumBO == null) {
            return false;
        }
        try {
            UserBO userBO = userService.findUserByName(forumBO.getUserName());
            if (userBO == null) {
                log.error("not a valid user");
                return false;
            }
            ForumPO forumPO = new ForumPO();
            forumPO.setUserFkey(userBO.getId());
            forumPO.setLikes(forumBO.getLikes());
            forumPO.setComments(forumBO.getComments());
            forumPO.setForwarding(forumBO.getForwarding());
            forumPO.setTitle(forumBO.getTitle());
            forumPO.setText(forumBO.getText());
            Date date = null;
            try {
                date = new Date(Long.parseLong(forumBO.getTimestamp()));
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

    @Override
    public boolean checkCid(Long cid) {
        QueryWrapper<ForumPO> forumPOQueryWrapper = new QueryWrapper<>();
        forumPOQueryWrapper.eq("id", cid);
        ForumPO forumPO = getOne(forumPOQueryWrapper);
        if (forumPO == null) {
            log.error("not a valid forum id");
            return false;
        }
        return true;
    }

    @Override
    public boolean changeLike(Long uid, LikeChanger likeChanger) {
        try {
            QueryWrapper<ForumPO> forumPOQueryWrapper = new QueryWrapper<>();
            forumPOQueryWrapper.eq("id", uid);
            ForumPO forumPO = getOne(forumPOQueryWrapper);
            forumPO.setLikes(likeChanger.updateLike(forumPO.getLikes()));
            updateById(forumPO);
        } catch (Exception e) {
            log.error("change like error");
            return false;
        }
        return true;
    }

    @Override
    public boolean changeComment(Long uid, CommentChanger commentChanger) {
        try {
            QueryWrapper<ForumPO> forumPOQueryWrapper = new QueryWrapper<>();
            forumPOQueryWrapper.eq("id", uid);
            ForumPO forumPO = getOne(forumPOQueryWrapper);
            forumPO.setComments(commentChanger.updateComment(forumPO.getComments()));
            updateById(forumPO);
        } catch (Exception e) {
            log.error("change comment error");
            return false;
        }
        return true;
    }
}

