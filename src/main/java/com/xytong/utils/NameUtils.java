package com.xytong.utils;

import lombok.extern.slf4j.Slf4j;

import static com.xytong.controller.CaptchaController.CAPTCHA_MODULE_NAME;
import static com.xytong.controller.CommentController.COMMENT_MODULE_NAME;
import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;
import static com.xytong.controller.LikeController.LIKE_MODULE_NAME;
import static com.xytong.controller.ReController.RE_MODULE_NAME;
import static com.xytong.controller.ShController.SH_MODULE_NAME;
import static com.xytong.controller.UserController.USER_MODULE_NAME;

@Slf4j
public class NameUtils {
    public static String module2Table(String module) {
        if (module == null) {
            return null;
        }
        switch (module) {
            case FORUM_MODULE_NAME:
                return "forum";
            case SH_MODULE_NAME:
                return "sh";
            case RE_MODULE_NAME:
                return "re";
            case COMMENT_MODULE_NAME:
                return "comment";
            case CAPTCHA_MODULE_NAME:
                return "captcha";
            case USER_MODULE_NAME:
                return "user";
            case LIKE_MODULE_NAME:
                return "like";
            default:
                return null;
        }
    }
}
