package com.xytong.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.data.json.domain.User;
import com.xytong.data.mapper.UserMapper;
import com.xytong.data.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author bszydxh
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-03 01:13:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}
