package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.ForumMapper;
import com.xytong.mapper.UserMapper;
import com.xytong.model.controllerData.ForumData;
import com.xytong.model.entity.Forum;
import com.xytong.service.ForumService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【forum】的数据库操作Service实现
 * @createDate 2022-09-03 01:13:25
 */
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
        implements ForumService {
    final ForumMapper forumMapper;
    final UserMapper userMapper;

    public ForumServiceImpl(ForumMapper forumMapper, UserMapper userMapper) {
        this.forumMapper = forumMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<ForumData> getForumList(String mode, int start, int end) {
        List<ForumData> forumList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                List<Forum> forums = forumMapper.selectList(null);
                for (Forum forum : forums) {
                    int uid = forum.getUserFkey();
                    forumList.add(forum.toForumDataWithUserData(userMapper.selectById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return forumList;
    }
}

