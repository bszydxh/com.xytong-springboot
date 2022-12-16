package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.factory.PO2BOFactory;
import com.xytong.mapper.CommentMapper;
import com.xytong.model.bo.CommentBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.CommentPO;
import com.xytong.service.*;
import com.xytong.utils.NameUtils;
import com.xytong.utils.CidUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;

/**
 * @author bszydxh
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentPO>
        implements CommentService {
    final UserService userService;

    final ForumService forumService;

    final ReService reService;

    final ShService shService;

    public CommentServiceImpl(UserService userService, ForumService forumService, ReService reService, ShService shService) {
        this.userService = userService;
        this.forumService = forumService;
        this.reService = reService;
        this.shService = shService;
    }

    @NotNull
    @Override
    public List<CommentBO> getCommentList(Long cid, String module, String mode, Long timestamp, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<CommentBO> commentList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                QueryWrapper<CommentPO> wrapper = new QueryWrapper<>();
                Date date = new Date(timestamp);
                wrapper.eq("card_fkey", cid);
                wrapper.eq("card_ftable", NameUtils.module2Table(module));
                //过滤新数据
                wrapper.le("timestamp", date);
                wrapper.last("ORDER BY `id` DESC LIMIT " + start + "," + (end - start + 1));
                List<CommentPO> commentPOList = list(wrapper);
                for (CommentPO commentPO : commentPOList) {
                    Long uid = commentPO.getUserFkey();
                    commentList.add(PO2BOFactory.getCommentBO(commentPO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return commentList;
    }

    @Override
    public Boolean addComment(CommentBO commentBO) {
        if (commentBO == null) {
            return false;
        }
        try {
            UserBO userBO = userService.findUserByName(commentBO.getUserName());
            if (userBO == null) {
                log.error("not a valid user");
                return false;
            }
            String module = commentBO.getModule();
            Long uid = commentBO.getCid();
            String table_name = NameUtils.module2Table(module);
            if (!CidUtils.isCidValid(module, uid)) {
                return false;
            }
            if (FORUM_MODULE_NAME.equals(module)) {
                forumService.changeComment(uid, (comments -> comments + 1));
            }
            CommentPO commentPO = new CommentPO();
            commentPO.setCardFkey(uid);
            commentPO.setCardFtable(table_name);
            commentPO.setUserFkey(userBO.getId());
            commentPO.setReplyFkey(0L);
            commentPO.setText(commentBO.getText());
            commentPO.setVisibility("visible");
            commentPO.setLikes(0);
            commentPO.setFloor(1);
            Date date;
            try {
                date = new Date(Long.parseLong(commentBO.getTimestamp()));
                commentPO.setTimestamp(date);
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("not a valid date");
            }
            save(commentPO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("save error");
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkCid(Long cid) {
        QueryWrapper<CommentPO> commentPOQueryWrapper = new QueryWrapper<>();
        commentPOQueryWrapper.eq("id", cid);
        CommentPO commentPO = getOne(commentPOQueryWrapper);
        if (commentPO == null) {
            log.error("not a valid comment id");
            return false;
        }
        return true;
    }
}
