package com.xytong.controller;

import com.xytong.data.json.AccessPostJson;
import com.xytong.data.json.AccessRequestJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {
    @RequestMapping(value = "/access", produces = "application/json")
    public AccessPostJson forumData(@RequestBody AccessRequestJson accessRequestJson) {
        Logger logger = LoggerFactory.getLogger(AccessController.class);
        logger.info(accessRequestJson.getTimestamp());
        return new AccessPostJson();
    }
}
