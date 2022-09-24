package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.mapper.ForumMapper;
import com.xytong.mapper.UserMapper;
import com.xytong.model.BO.ForumBO;
import com.xytong.model.PO.ForumPO;
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
public class ForumServiceImpl extends ServiceImpl<ForumMapper, ForumPO>
        implements ForumService {
    final ForumMapper forumMapper;
    final UserMapper userMapper;

    public ForumServiceImpl(ForumMapper forumMapper, UserMapper userMapper) {
        this.forumMapper = forumMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<ForumBO> getForumList(String mode, int start, int end) {
        List<ForumBO> forumList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                List<ForumPO> forumPOList = forumMapper.selectList(null);
                for (ForumPO forumPO : forumPOList) {
                    int uid = forumPO.getUserFkey();
                    forumList.add(new ForumBO(forumPO, userMapper.selectById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return forumList;
    }
}

