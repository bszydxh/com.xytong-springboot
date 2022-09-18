package com.xytong.controller;

import com.xytong.mapper.ForumMapper;
import com.xytong.mapper.UserMapper;
import com.xytong.model.controllerData.ForumData;
import com.xytong.model.controllerData.json.ForumPostJson;
import com.xytong.model.controllerData.json.ForumRequestJson;
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
    public ForumPostJson forumData(@RequestBody ForumRequestJson forumRequestJson) {
        ForumPostJson forumPostJson = new ForumPostJson();
        if (!Objects.equals(forumRequestJson.getModule(), "forums")) {
            forumPostJson.setMode("module error");
            return forumPostJson;
        }
        int start = forumRequestJson.getNumStart();
        int end = forumRequestJson.getNumEnd();
        int num = forumRequestJson.getNeedNum();
        if (start > end || end - start != num - 1) {
            forumPostJson.setMode("num error");
        } else {
            forumPostJson.setMode(forumRequestJson.getMode());
            forumPostJson.setNumStart(start);
            forumPostJson.setNeedNum(num);
            forumPostJson.setNumEnd(end);
            forumPostJson.setTimestamp(System.currentTimeMillis());
            try {
                List<ForumData> forumList = forumService.getForumList(forumRequestJson.getMode(), start, end);
                forumPostJson.setForumData(forumList);
            } catch (Exception e) {
                forumPostJson.setMode("mode error");
            }
        }
        return forumPostJson;
    }
}
