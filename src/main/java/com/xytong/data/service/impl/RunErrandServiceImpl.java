package com.xytong.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.data.domain.Re;
import com.xytong.data.mapper.ReMapper;
import com.xytong.data.service.RunErrandService;
import org.springframework.stereotype.Service;

/**
* @author bszydxh
* @description 针对表【run_errand】的数据库操作Service实现
* @createDate 2022-09-05 21:07:29
*/
@Service
public class RunErrandServiceImpl extends ServiceImpl<ReMapper, Re>
implements RunErrandService{

}
