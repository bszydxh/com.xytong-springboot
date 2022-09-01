package com.xytong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    private List<String> JsonList = new ArrayList<>();
    String string = "";
    String json_a = "{\n" +
            "    \"category\": \"工作\",\n" +
            "    \"content\": \"下午2点开始工作\",\n" +
            "    \"createAt\": \"2020-01-01 12:00:00\"\n" +
            "}";

    @GetMapping("/test")
    public String index() {
        JsonList.clear();
        string = "";
        for (int i = 0; i < 3; i++) {
            JsonList.add(json_a);
        }
        for (int i = 0; i < JsonList.size(); i++) {
            string = string + JsonList.get(i) + ",\n";
        }
        return string;
    }
}
