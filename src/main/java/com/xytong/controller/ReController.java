package com.xytong.controller;

import com.xytong.model.controllerData.ReData;
import com.xytong.model.entity.Re;
import com.xytong.model.controllerData.json.RePostJson;
import com.xytong.model.controllerData.json.ReRequestJson;
import com.xytong.mapper.ReMapper;
import com.xytong.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ReController {
    final ReMapper reMapper;
    final UserMapper userMapper;

    public ReController(ReMapper reMapper, UserMapper userMapper) {
        this.reMapper = reMapper;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/run_errands", produces = "application/json")
    public RePostJson reData(@RequestBody ReRequestJson reRequestJson) {
        RePostJson rePostJson = new RePostJson();
        if(!Objects.equals(reRequestJson.getModule(), "run_errands"))
        {
            rePostJson.setMode("module error");
            return rePostJson;
        }
        switch (reRequestJson.getMode()) {
            case ("newest"): {
                int start = reRequestJson.getNumStart();
                int end = reRequestJson.getNumEnd();
                int num = reRequestJson.getNeedNum();
                if (start > end || end - start != num - 1) {
                    rePostJson.setMode("num error");
                } else {
                    rePostJson.setMode(reRequestJson.getMode());
                    rePostJson.setNumStart(start);
                    rePostJson.setNeedNum(num);
                    rePostJson.setNumEnd(end);
                    rePostJson.setTimestamp(System.currentTimeMillis());
                    List<ReData> reList = new ArrayList<>();
                    List<Re> res = reMapper.selectList(null);
                    for (Re re : res) {
                        int uid = re.getUserFkey();
                        reList.add(re.toReDataWithUserData(userMapper.selectById(uid)));
                    }
                    rePostJson.setReData(reList);
                }
                break;
            }
            default: {
                rePostJson.setMode("mode error");
            }
        }


        return rePostJson;
    }
}
