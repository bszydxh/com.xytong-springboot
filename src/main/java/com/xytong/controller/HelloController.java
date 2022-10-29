package com.xytong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author bszydxh
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/bszydxh", method = RequestMethod.GET)
    public String sayHello() {
        return "hello xytong";
    }
}