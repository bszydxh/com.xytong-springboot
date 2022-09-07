package com.xytong.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.data.domain.Sh;
import com.xytong.data.mapper.ShMapper;
import com.xytong.data.service.SecondhandService;
import org.springframework.stereotype.Service;

/**
* @author bszydxh
* @description 针对表【secondhand】的数据库操作Service实现
* @createDate 2022-09-05 21:07:29
*/
@Service
public class SecondhandServiceImpl extends ServiceImpl<ShMapper, Sh>
implements SecondhandService{

}
