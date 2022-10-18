package com.xytong.controller;

import com.xytong.model.bo.ForumBO;
import com.xytong.model.dto.forum.ForumAddPostDTO;
import com.xytong.model.dto.forum.ForumAddRequestDTO;
import com.xytong.model.dto.forum.ForumGetPostDTO;
import com.xytong.model.dto.forum.ForumGetRequestDTO;
import com.xytong.service.ForumService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ForumController {

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    final ForumService forumService;


    @RequestMapping(value = "/forums", produces = "application/json")
    @ResponseBody
    public ForumGetPostDTO getForumList(@RequestBody ForumGetRequestDTO forumGetRequestDTO) {
        ForumGetPostDTO forumGetPostDTO = new ForumGetPostDTO();
        if (!Objects.equals(forumGetRequestDTO.getModule(), "forums")) {
            forumGetPostDTO.setMode("module error");
            return forumGetPostDTO;
        }
        int start = forumGetRequestDTO.getNumStart();
        int end = forumGetRequestDTO.getNumEnd();
        int num = forumGetRequestDTO.getNeedNum();
        if (start > end || end - start != num - 1) {
            forumGetPostDTO.setMode("num error");
        } else {
            forumGetPostDTO.setMode(forumGetRequestDTO.getMode());
            forumGetPostDTO.setNumStart(start);
            forumGetPostDTO.setNeedNum(num);
            forumGetPostDTO.setNumEnd(end);
            forumGetPostDTO.setTimestamp(System.currentTimeMillis());
            try {
                List<ForumBO> forumList = forumService.getForumList(forumGetRequestDTO.getMode(), start, end);
                forumGetPostDTO.setForumData(forumList);
            } catch (Exception e) {
                forumGetPostDTO.setMode("mode error");
            }
        }
        return forumGetPostDTO;
    }

    @RequestMapping(value = "/forums/add", produces = "application/json")
    @ResponseBody
    public ForumAddPostDTO addForumList(@RequestBody ForumAddRequestDTO forumRequestDTO) {
        ForumAddPostDTO forumAddPostDTO = new ForumAddPostDTO();

        return forumAddPostDTO;
    }
}
