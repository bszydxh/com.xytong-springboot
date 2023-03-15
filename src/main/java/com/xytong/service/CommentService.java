package com.xytong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xytong.model.bo.CommentBO;
import com.xytong.model.po.CommentPO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【comment】的数据库操作Service
 * @createDate 2022-10-26 22:54:29
 */

public interface CommentService extends IService<CommentPO> {
    @NotNull
    List<CommentBO> getCommentList(Long cid, String module, String mode, Long timestamp, int start, int end);

    boolean addComment(CommentBO commentBO);

    boolean checkCid(Long cid);

}
