package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.ForumMapper;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.po.ForumPO;
import com.xytong.service.ForumService;
import com.xytong.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【forum】的数据库操作Service实现
 * @createDate 2022-09-03 01:13:25
 */
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
                    int uid = forumPO.getUserFkey();
                    forumList.add(ForumBO.init(forumPO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return forumList;
    }
}

