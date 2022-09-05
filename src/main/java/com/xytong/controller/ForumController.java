package com.xytong.controller;

import com.xytong.data.ForumData;
import com.xytong.data.json.ForumPostJson;
import com.xytong.data.json.ForumRequestJson;
import com.xytong.data.json.domain.Forum;
import com.xytong.data.mapper.ForumMapper;
import com.xytong.data.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ForumController {
    final ForumMapper forumMapper;
    final UserMapper userMapper;

    public ForumController(ForumMapper forumMapper, UserMapper userMapper) {
        this.forumMapper = forumMapper;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/forums", produces = "application/json")
    public ForumPostJson forumData(@RequestBody ForumRequestJson forumRequestJson) {
        ForumPostJson forumPostJson = new ForumPostJson();
        if(!Objects.equals(forumRequestJson.getModule(), "forums"))
        {
            forumPostJson.setMode("module error");
            return forumPostJson;
        }
        switch (forumRequestJson.getMode()) {
            case ("newest"): {
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
                    List<ForumData> forumList = new ArrayList<>();
                    List<Forum> forums = forumMapper.selectList(null);
                    for (Forum forum : forums) {
                        int uid = forum.getUserFkey();
                        forumList.add(forum.toForumDataWithUserData(userMapper.selectById(uid)));
                    }
                    forumPostJson.setForumData(forumList);
                }
                break;
            }
            default: {
                forumPostJson.setMode("mode error");
            }
        }


        return forumPostJson;
    }
}
