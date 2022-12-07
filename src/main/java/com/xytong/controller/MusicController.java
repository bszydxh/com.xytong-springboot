package com.xytong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bszydxh
 */
@Controller
public class MusicController {
    @RequestMapping("/music")
    public String redirect(){
        return "redirect:zwddzp.flac";
    }
}
