package com.xytong.controller;

import com.xytong.mapper.ForumMapper;
import com.xytong.mapper.UserMapper;
import com.xytong.model.BO.ForumBO;
import com.xytong.model.DTO.ForumPostDTO;
import com.xytong.model.DTO.ForumRequestDTO;
import com.xytong.service.ForumService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ForumController {
    final ForumMapper forumMapper;
    final UserMapper userMapper;

    public ForumController(ForumMapper forumMapper, UserMapper userMapper, ForumService forumService) {
        this.forumMapper = forumMapper;
        this.userMapper = userMapper;
        this.forumService = forumService;
    }

    final ForumService forumService;

    @RequestMapping(value = "/forums", produces = "application/json")
    public ForumPostDTO forumData(@RequestBody ForumRequestDTO forumRequestDTO) {
        ForumPostDTO forumPostJson = new ForumPostDTO();
        if (!Objects.equals(forumRequestDTO.getModule(), "forums")) {
            forumPostJson.setMode("module error");
            return forumPostJson;
        }
        int start = forumRequestDTO.getNumStart();
        int end = forumRequestDTO.getNumEnd();
        int num = forumRequestDTO.getNeedNum();
        if (start > end || end - start != num - 1) {
            forumPostJson.setMode("num error");
        } else {
            forumPostJson.setMode(forumRequestDTO.getMode());
            forumPostJson.setNumStart(start);
            forumPostJson.setNeedNum(num);
            forumPostJson.setNumEnd(end);
            forumPostJson.setTimestamp(System.currentTimeMillis());
            try {
                List<ForumBO> forumList = forumService.getForumList(forumRequestDTO.getMode(), start, end);
                forumPostJson.setForumData(forumList);
            } catch (Exception e) {
                forumPostJson.setMode("mode error");
            }
        }
        return forumPostJson;
    }
}
