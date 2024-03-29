package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.UserPO;

/**
 * @author bszydxh
 * @description 针对表【user】的数据库操作Service
 * @createDate 2022-09-08 18:36:30
 */
public interface UserService extends IService<UserPO> {
    boolean checkUser(String username, String password);

    UserBO findUserByName(String username);

    UserBO findUserById(Long id);

    UserBO findUserByEmail(String email);

    /**
     * 只负责添加，不负责校验
     *
     * @param userBO userBO
     * @return 添加结果
     */
    boolean addUser(UserBO userBO);
}
