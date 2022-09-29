package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.ShBO;
import com.xytong.model.po.ShPO;

import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【secondhand】的数据库操作Service
 * @createDate 2022-09-05 21:07:29
 */
public interface SecondhandService extends IService<ShPO> {
    List<ShBO> getShList(String mode, int start, int end);
}
