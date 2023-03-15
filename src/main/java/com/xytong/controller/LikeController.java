package com.xytong.controller;

import com.xytong.model.bo.LikeBO;
import com.xytong.model.dto.like.LikeAddRequestDTO;
import com.xytong.model.dto.like.LikeAddResponseDTO;
import com.xytong.model.dto.like.LikeDeleteRequestDTO;
import com.xytong.model.dto.like.LikeDeleteResponseDTO;
import com.xytong.service.AccessService;
import com.xytong.service.LikeService;
import com.xytong.service.UserService;
import com.xytong.utils.NameUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.xytong.controller.CommentController.COMMENT_MODULE_NAME;
import static com.xytong.controller.ForumController.FORUM_MODULE_NAME;

/**
 * @author bszydxh
 */
@RestController
public class LikeController {
    public static final String LIKE_MODULE_NAME = "like";
    final LikeService likeService;
    final AccessService accessService;

    final UserService userService;

    public LikeController(LikeService likeService, AccessService accessService, UserService userService) {
        this.likeService = likeService;
        this.accessService = accessService;
        this.userService = userService;
    }

    @RequestMapping(value = "/" + LIKE_MODULE_NAME + "/v1/add", produces = "application/json")
    @ResponseBody
    public LikeAddResponseDTO addLike(@RequestBody LikeAddRequestDTO likeAddRequestDTO) {
        LikeAddResponseDTO likeAddResponseDTO = new LikeAddResponseDTO();
        likeAddResponseDTO.setTimestamp(System.currentTimeMillis());
        if (!accessService.tokenCheckerWithUsername(likeAddRequestDTO.getToken(), likeAddRequestDTO.getUsername())) {
            likeAddResponseDTO.setMsg("token error");
            return likeAddResponseDTO;
        }
        if (likeAddRequestDTO.getCid() == null || likeAddRequestDTO.getCid().trim().equals("")) {
            likeAddResponseDTO.setMsg("cid error");
            return likeAddResponseDTO;
        }
        if (NameUtils.module2Table(likeAddRequestDTO.getModule()) == null) {
            if(!likeAddRequestDTO.getModule().equals(COMMENT_MODULE_NAME)&&
                    !likeAddRequestDTO.getModule().equals(FORUM_MODULE_NAME))
            {
                likeAddResponseDTO.setMsg("module error");
                return likeAddResponseDTO;
            }

        }
        LikeBO likeBO = new LikeBO();
        likeBO.setModule(likeAddRequestDTO.getModule());
        likeBO.setUid(userService.findUserByName(likeAddRequestDTO.getUsername()).getId());
        likeBO.setCid(Long.valueOf(likeAddRequestDTO.getCid()));
        if (likeService.addLike(likeBO)) {
            likeAddResponseDTO.setMsg("add ok");
        } else {
            likeAddResponseDTO.setMsg("add error");
        }
        return likeAddResponseDTO;
    }
    @RequestMapping(value = "/" + LIKE_MODULE_NAME + "/v1/delete", produces = "application/json")
    @ResponseBody
    public LikeDeleteResponseDTO addLike(@RequestBody LikeDeleteRequestDTO likeDeleteRequestDTO) {
        LikeDeleteResponseDTO likeDeleteResponseDTO = new LikeDeleteResponseDTO();
        likeDeleteResponseDTO.setTimestamp(System.currentTimeMillis());
        if (!accessService.tokenCheckerWithUsername(likeDeleteRequestDTO.getToken(), likeDeleteRequestDTO.getUsername())) {
            likeDeleteResponseDTO.setMsg("token error");
            return likeDeleteResponseDTO;
        }
        if (likeDeleteRequestDTO.getCid() == null || likeDeleteRequestDTO.getCid().trim().equals("")) {
            likeDeleteResponseDTO.setMsg("cid error");
            return likeDeleteResponseDTO;
        }
        if (NameUtils.module2Table(likeDeleteRequestDTO.getModule()) == null) {
            if(!likeDeleteRequestDTO.getModule().equals(COMMENT_MODULE_NAME)&&
                    !likeDeleteRequestDTO.getModule().equals(FORUM_MODULE_NAME))
            {
                likeDeleteResponseDTO.setMsg("module error");
                return likeDeleteResponseDTO;
            }

        }
        LikeBO likeBO = new LikeBO();
        likeBO.setModule(likeDeleteRequestDTO.getModule());
        likeBO.setUid(userService.findUserByName(likeDeleteRequestDTO.getUsername()).getId());
        likeBO.setCid(Long.valueOf(likeDeleteRequestDTO.getCid()));
//        if (likeService.deleteLike(likeBO)) {
//            likeDeleteResponseDTO.setMsg("delete ok");
//        } else {
//            likeDeleteResponseDTO.setMsg("delete error");
//        }
        return likeDeleteResponseDTO;
    }
}
