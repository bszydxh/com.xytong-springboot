package com.xytong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.model.po.CommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author bszydxh
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2022-10-26 22:54:29
* @Entity com.xytong.model.po.CommentPO
*/
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<CommentPO> {


}
