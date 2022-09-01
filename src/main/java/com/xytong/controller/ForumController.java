package com.xytong.controller;

import com.xytong.data.ForumData;
import com.xytong.data.Json.ForumJson;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ForumController {
    @RequestMapping("/forums")
    @ResponseBody
    public ForumJson forumData() {
        Logger log = LoggerFactory.getLogger(this.getClass());
        ForumJson forumJson = new ForumJson();
        forumJson.setMode("newest");
        forumJson.setNumStart(0);
        forumJson.setNeedNum(10);
        forumJson.setNumEnd(9);
        forumJson.setTimestamp(12003103);
        List<ForumData> forumList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ForumData forumData = new ForumData();
            forumData.setUserAvatarUrl("https://profile.csdnimg.cn/B/7/E/1_qq_51675557");
            forumData.setUserName("root");
            forumData.setTitle("test" + i);
            forumData.setText("bszydxh-laptop-test" + i);
            forumData.setForwarding(11);
            forumData.setComments(45);
            forumData.setLikes(14);
            forumList.add(forumData);
        }
        forumJson.setForumData(forumList);
        return forumJson;
    }
}
