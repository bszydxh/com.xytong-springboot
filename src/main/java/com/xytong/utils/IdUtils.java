package com.xytong.utils;

import com.xytong.service.CommentService;
import com.xytong.service.ForumService;
import com.xytong.service.ReService;
import com.xytong.service.ShService;
import lombok.extern.slf4j.Slf4j;

import static com.xytong.controller.CommentController.COMMENT_MODULE_NAME;
import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static com.xytong.controller.ReController.RE_MODULE_NAME;
import static com.xytong.controller.ShController.SH_MODULE_NAME;

@Slf4j
public class IdUtils {
    public static Boolean isCidValid(String module, Long cid) {
        if (module == null || "".equals(module.trim())) {
            log.warn("module null");
            return false;
        }
        if (cid == null) {
            log.warn("cid null");
            return false;
        }
        ShService shService = BeanCreateUtils.getBeanByClass(ShService.class);
        ReService reService = BeanCreateUtils.getBeanByClass(ReService.class);
        ForumService forumService = BeanCreateUtils.getBeanByClass(ForumService.class);
        CommentService commentService = BeanCreateUtils.getBeanByClass(CommentService.class);
        switch (module) {
            case SH_MODULE_NAME:
                if (shService.checkCid(cid)) {
                    return true;
                }
                break;
            case RE_MODULE_NAME:
                if (reService.checkCid(cid)) {
                    return true;
                }
                break;
            case FORUM_MODULE_NAME:
                if (forumService.checkCid(cid)) {
                    return true;
                }
                break;
            case COMMENT_MODULE_NAME:
                if (commentService.checkCid(cid)) {
                    return true;
                }
                break;
        }
        log.error("not a valid table or cid");
        return false;
    }


}
