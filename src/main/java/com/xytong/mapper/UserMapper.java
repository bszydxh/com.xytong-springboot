package com.xytong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author auto_generator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-08 18:36:30
* @Entity com.xytong.model.po.UserPO
*/
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserPO> {
}
