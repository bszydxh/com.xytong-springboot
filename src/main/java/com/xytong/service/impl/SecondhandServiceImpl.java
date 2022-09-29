package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.model.bo.ShBO;
import com.xytong.model.po.ShPO;
import com.xytong.mapper.ShMapper;
import com.xytong.service.SecondhandService;
import com.xytong.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【secondhand】的数据库操作Service实现
 * @createDate 2022-09-05 21:07:29
 */
@Service
public class SecondhandServiceImpl extends ServiceImpl<ShMapper, ShPO>
        implements SecondhandService {
    final UserService userService;

    public SecondhandServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<ShBO> getShList(String mode, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<ShBO> shList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                List<ShPO> shPOList = list();
                for (ShPO forumPO : shPOList) {
                    int uid = forumPO.getUserFkey();
                    shList.add(ShBO.init(forumPO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return shList;
    }
}
