package com.xytong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.model.PO.ShPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author bszydxh
* @description 针对表【secondhand】的数据库操作Mapper
* @createDate 2022-09-05 21:07:29
* @Entity generator.domain.Secondhand
*/
@Mapper
@Repository
public interface ShMapper extends BaseMapper<ShPO> {
}
