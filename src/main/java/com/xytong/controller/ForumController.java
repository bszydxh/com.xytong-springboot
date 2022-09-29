package com.xytong.controller;

import com.xytong.mapper.ForumMapper;
import com.xytong.mapper.UserMapper;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.dto.ForumPostDTO;
import com.xytong.model.dto.ForumRequestDTO;
import com.xytong.service.ForumService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ForumPostDTO forumData(@RequestBody ForumRequestDTO forumRequestDTO) {
        ForumPostDTO forumPostDTO = new ForumPostDTO();
        if (!Objects.equals(forumRequestDTO.getModule(), "forums")) {
            forumPostDTO.setMode("module error");
            return forumPostDTO;
        }
        int start = forumRequestDTO.getNumStart();
        int end = forumRequestDTO.getNumEnd();
        int num = forumRequestDTO.getNeedNum();
        if (start > end || end - start != num - 1) {
            forumPostDTO.setMode("num error");
        } else {
            forumPostDTO.setMode(forumRequestDTO.getMode());
            forumPostDTO.setNumStart(start);
            forumPostDTO.setNeedNum(num);
            forumPostDTO.setNumEnd(end);
            forumPostDTO.setTimestamp(System.currentTimeMillis());
            try {
                List<ForumBO> forumList = forumService.getForumList(forumRequestDTO.getMode(), start, end);
                forumPostDTO.setForumData(forumList);
            } catch (Exception e) {
                forumPostDTO.setMode("mode error");
            }
        }
        return forumPostDTO;
    }
}
