package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.model.entity.User;
import com.xytong.mapper.UserMapper;
import com.xytong.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author bszydxh
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-09-08 18:36:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public boolean checkUser(String username, String password) {
        if(username == null || password == null)
        {
            return false;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ge("name", username);
        Map<String, Object> map = getMap(queryWrapper);
        return Objects.equals(map.get("password"), password);
    }
}
