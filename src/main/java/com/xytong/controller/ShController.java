package com.xytong.controller;

import com.xytong.data.ShData;
import com.xytong.data.json.ShPostJson;
import com.xytong.data.json.ShRequestJson;
import com.xytong.data.json.domain.Sh;
import com.xytong.data.mapper.ShMapper;
import com.xytong.data.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ShController {
    final ShMapper shMapper;
    final UserMapper userMapper;

    public ShController(ShMapper shMapper, UserMapper userMapper) {
        this.shMapper = shMapper;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/secondhand", produces = "application/json")
    public ShPostJson shData(@RequestBody ShRequestJson shRequestJson) {
        ShPostJson shPostJson = new ShPostJson();
        if (!Objects.equals(shRequestJson.getModule(), "secondhand")) {
            shPostJson.setMode("module error");
            return shPostJson;
        }
        switch (shRequestJson.getMode()) {
            case ("newest"): {
                int start = shRequestJson.getNumStart();
                int end = shRequestJson.getNumEnd();
                int num = shRequestJson.getNeedNum();
                if (start > end || end - start != num - 1) {
                    shPostJson.setMode("num error");
                } else {
                    shPostJson.setMode(shRequestJson.getMode());
                    shPostJson.setNumStart(start);
                    shPostJson.setNeedNum(num);
                    shPostJson.setNumEnd(end);
                    shPostJson.setTimestamp(System.currentTimeMillis());
                    List<ShData> shList = new ArrayList<>();
                    List<Sh> shs = shMapper.selectList(null);
                    for (Sh sh : shs) {
                        int uid = sh.getUserFkey();
                        shList.add(sh.toShDataWithUserData(userMapper.selectById(uid)));
                    }
                    shPostJson.setShData(shList);
                }
                break;
            }
            default: {
                shPostJson.setMode("mode error");
            }
        }


        return shPostJson;
    }
}
