package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.factory.PO2BOFactory;
import com.xytong.mapper.CommentMapper;
import com.xytong.model.bo.CommentBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.CommentPO;
import com.xytong.model.po.ForumPO;
import com.xytong.model.po.RePO;
import com.xytong.model.po.ShPO;
import com.xytong.service.*;
import com.xytong.utils.NameUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static com.xytong.controller.ReController.RE_MODULE_NAME;
import static com.xytong.controller.ShController.SH_MODULE_NAME;

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
                log.error(module+":"+NameUtils.Module2Table(module));
                wrapper.eq("card_ftable", NameUtils.Module2Table(module));
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
            String table_name = NameUtils.Module2Table(module);
            switch (module) {
                case SH_MODULE_NAME:
                    QueryWrapper<ShPO> shPOQueryWrapper = new QueryWrapper<>();
                    shPOQueryWrapper.eq("id", uid);
                    ShPO shPO = shService.getOne(shPOQueryWrapper);
                    if (shPO == null) {
                        log.error("not a valid sh id");
                        return false;
                    }
                    break;
                case RE_MODULE_NAME:
                    QueryWrapper<RePO> rePOQueryWrapper = new QueryWrapper<>();
                    rePOQueryWrapper.eq("id", uid);
                    RePO rePO = reService.getOne(rePOQueryWrapper);
                    if (rePO == null) {
                        log.error("not a valid re id");
                        return false;
                    }
                    break;
                case FORUM_MODULE_NAME:
                    QueryWrapper<ForumPO> forumPOQueryWrapper = new QueryWrapper<>();
                    forumPOQueryWrapper.eq("id", uid);
                    ForumPO forumPO = forumService.getOne(forumPOQueryWrapper);
                    if (forumPO == null) {
                        log.error("not a valid forum id");
                        return false;
                    }
                    forumPO.setComments(forumPO.getComments() + 1);
                    forumService.updateById(forumPO);
                    break;
                default:
                    log.error("not a valid table");
                    return false;

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
}
