package com.xytong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.model.PO.ForumPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* @author bszydxh
* @description 针对表【forum】的数据库操作Mapper
* @createDate 2022-09-03 01:13:25
* @Entity generator.domain.Forum
*/
@Mapper
@Repository
public interface ForumMapper extends BaseMapper<ForumPO> {

}
