package com.xytong.controller;

import com.xytong.factory.BO2DTOFactory;
import com.xytong.model.bo.CommentBO;
import com.xytong.model.dto.comment.CommentGetRequestDTO;
import com.xytong.model.dto.comment.CommentGetResponseDTO;
import com.xytong.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CommentController {
    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public static final String COMMENT_MODULE_NAME = "comment";

    @RequestMapping(value = "/comment", produces = "application/json")
    @ResponseBody
    public CommentGetResponseDTO getCommentList(@RequestBody CommentGetRequestDTO commentGetRequestDTO) {
        if (commentGetRequestDTO.getModule() == null) {
            return BO2DTOFactory.getBbsGetResponseDTO("module error", CommentGetResponseDTO.class);
        }
        Integer start = commentGetRequestDTO.getNumStart();
        Integer end = commentGetRequestDTO.getNumEnd();
        Integer num = commentGetRequestDTO.getNeedNum();
        if (start == null || end == null || num == null) {
            return BO2DTOFactory.getBbsGetResponseDTO("interface error", CommentGetResponseDTO.class);
        }
        if (start > end || end - start != num - 1) {
            return BO2DTOFactory.getBbsGetResponseDTO("num error", CommentGetResponseDTO.class);
        } else {
            try {
                List<CommentBO> commentList = commentService.getCommentList(
                        commentGetRequestDTO.getCid(),
                        commentGetRequestDTO.getModule(),
                        commentGetRequestDTO.getMode(),
                        commentGetRequestDTO.getTimestamp(),
                        start,
                        end);
                return BO2DTOFactory.getBbsGetResponseDTO(
                        commentGetRequestDTO.getMode(),
                        commentGetRequestDTO.getTimestamp(),
                        start,
                        start + commentList.size() - 1,
                        commentList,
                        CommentGetResponseDTO.class);
            } catch (Exception e) {
                return BO2DTOFactory.getBbsGetResponseDTO("mode error", CommentGetResponseDTO.class);
            }
        }
    }

    @RequestMapping(value = "/comment/v1/get", produces = "application/json")
    @ResponseBody
    public CommentGetResponseDTO getCommentList2(@RequestBody CommentGetRequestDTO commentGetRequestDTO) {
        return getCommentList(commentGetRequestDTO);
    }

}
