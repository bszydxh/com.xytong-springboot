package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.po.ForumPO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【forum】的数据库操作Service
 * @createDate 2022-09-03 01:13:25
 */
public interface ForumService extends IService<ForumPO> {

    /**
     * @param mode      查询模式
     * @param timestamp 控制版本的时间戳
     * @param start     起始标记
     * @param end       结束标记
     * @return ForumBO表
     */
    @NotNull
    List<ForumBO> getForumList(String mode, Long timestamp, int start, int end);

    Boolean addForum(ForumBO forumBO);
}
