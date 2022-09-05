package com.xytong.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.data.json.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author bszydxh
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-03 01:13:25
* @Entity generator.domain.User
*/

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
