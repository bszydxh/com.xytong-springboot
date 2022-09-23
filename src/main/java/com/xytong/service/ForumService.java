package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.BO.ForumBO;
import com.xytong.model.PO.ForumPO;

import java.util.List;

/**
* @author bszydxh
* @description 针对表【forum】的数据库操作Service
* @createDate 2022-09-03 01:13:25
*/
public interface ForumService extends IService<ForumPO> {

    List<ForumBO> getForumList(String mode, int start, int end);
}
