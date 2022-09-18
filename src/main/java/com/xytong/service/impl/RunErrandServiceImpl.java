package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.model.entity.Re;
import com.xytong.mapper.ReMapper;
import com.xytong.service.RunErrandService;
import org.springframework.stereotype.Service;

/**
* @author bszydxh
* @description 针对表【run_errand】的数据库操作Service实现
* @createDate 2022-09-05 21:07:29
*/
@Service
public class RunErrandServiceImpl extends ServiceImpl<ReMapper, Re>
implements RunErrandService {

}