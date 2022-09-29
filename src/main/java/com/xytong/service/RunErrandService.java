package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.ReBO;
import com.xytong.model.po.RePO;

import java.util.List;

/**
* @author bszydxh
* @description 针对表【run_errand】的数据库操作Service
* @createDate 2022-09-05 21:07:29
*/
public interface RunErrandService extends IService<RePO> {
    List<ReBO> getReList(String mode, int start, int end);

}
