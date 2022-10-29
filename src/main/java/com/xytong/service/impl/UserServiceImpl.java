package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.factory.PO2BOFactory;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.UserPO;
import com.xytong.mapper.UserMapper;
import com.xytong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author bszydxh
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-09-08 18:36:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO>
        implements UserService {

    @Override
    public boolean checkUser(String username, String pwd) {
        if (username == null || pwd == null) {
            return false;
        }
        UserBO userBO = findUserByName(username);
        if (userBO == null) {
            return false;
        }
        return Objects.equals(userBO.getPassword(), pwd);
    }

    @Override
    public UserBO findUserByName(String username) {
        if (username == null) {
            return null;
        }
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        UserPO userPO = null;
        try {
            userPO = getOne(queryWrapper);
        } catch (Exception e) {
            log.error("Find user error:" + username);
            e.printStackTrace();
        }
        return PO2BOFactory.getUserBO(userPO);
    }

    @Override
    public UserBO findUserById(Long id) {
        if (id == null) {
            return null;
        }
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        UserPO userPO = null;
        try {
            userPO = getOne(queryWrapper);
        } catch (Exception e) {
            log.error("Find id error:" + id);
            e.printStackTrace();
        }
        return PO2BOFactory.getUserBO(userPO);
    }

}
