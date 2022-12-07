package com.xytong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.model.po.ShPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author auto_generator
* @description 针对表【sh】的数据库操作Mapper
* @createDate 2022-09-05 21:07:29
* @Entity com.xytong.model.po.ShPO
*/
@Mapper
@Repository
public interface ShMapper extends BaseMapper<ShPO> {
}
