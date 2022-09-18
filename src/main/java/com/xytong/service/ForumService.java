package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.controllerData.ForumData;
import com.xytong.model.entity.Forum;

import java.util.List;

/**
* @author bszydxh
* @description 针对表【forum】的数据库操作Service
* @createDate 2022-09-03 01:13:25
*/
public interface ForumService extends IService<Forum> {

    List<ForumData> getForumList(String mode, int start, int end);
}
