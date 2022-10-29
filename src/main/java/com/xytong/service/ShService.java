package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.ReBO;
import com.xytong.model.bo.ShBO;
import com.xytong.model.po.ShPO;

import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【secondhand】的数据库操作Service
 * @createDate 2022-09-05 21:07:29
 */
public interface ShService extends IService<ShPO> {
    List<ShBO> getShList(String mode, Long timestamp, int start, int end);

    Boolean addSh(ShBO shBO);
}
