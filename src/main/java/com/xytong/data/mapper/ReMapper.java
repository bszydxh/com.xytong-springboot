package com.xytong.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.data.domain.Re;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author bszydxh
* @description 针对表【run_errand】的数据库操作Mapper
* @createDate 2022-09-05 21:07:29
* @Entity generator.domain.RunErrand
*/
@Mapper
@Repository
public interface ReMapper extends BaseMapper<Re> {


}
