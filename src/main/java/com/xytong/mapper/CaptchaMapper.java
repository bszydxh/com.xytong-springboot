package com.xytong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xytong.model.po.CaptchaPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author auto_generator
* @description 针对表【captcha】的数据库操作Mapper
* @createDate 2022-10-30 16:09:04
* @Entity com.xytong.model.po.CaptchaPO
*/
@Mapper
@Repository
public interface CaptchaMapper extends BaseMapper<CaptchaPO> {


}
