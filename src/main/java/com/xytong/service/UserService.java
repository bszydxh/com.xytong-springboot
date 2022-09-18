package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.entity.User;

/**
* @author bszydxh
* @description 针对表【user】的数据库操作Service
* @createDate 2022-09-08 18:36:30
*/
public interface UserService extends IService<User> {
    boolean checkUser(String username, String password);
}
