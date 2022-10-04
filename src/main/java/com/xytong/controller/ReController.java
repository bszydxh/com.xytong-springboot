package com.xytong.controller;

import com.xytong.model.bo.ReBO;
import com.xytong.model.po.RePO;
import com.xytong.model.dto.RePostDTO;
import com.xytong.model.dto.ReRequestDTO;
import com.xytong.mapper.ReMapper;
import com.xytong.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public RePostDTO getReList(@RequestBody ReRequestDTO reRequestDTO) {
        RePostDTO rePostDTO = new RePostDTO();
        if (!Objects.equals(reRequestDTO.getModule(), "run_errands")) {
            rePostDTO.setMode("module error");
            return rePostDTO;
        }
        switch (reRequestDTO.getMode()) {
            case ("newest"): {
                int start = reRequestDTO.getNumStart();
                int end = reRequestDTO.getNumEnd();
                int num = reRequestDTO.getNeedNum();
                if (start > end || end - start != num - 1) {
                    rePostDTO.setMode("num error");
                } else {
                    rePostDTO.setMode(reRequestDTO.getMode());
                    rePostDTO.setNumStart(start);
                    rePostDTO.setNeedNum(num);
                    rePostDTO.setNumEnd(end);
                    rePostDTO.setTimestamp(System.currentTimeMillis());
                    List<ReBO> reList = new ArrayList<>();
                    List<RePO> rePOList = reMapper.selectList(null);
                    for (RePO rePO : rePOList) {
                        int uid = rePO.getUserFkey();
                        reList.add(ReBO.init(rePO, userMapper.selectById(uid)));
                    }
                    rePostDTO.setReData(reList);
                }
                break;
            }
            default: {
                rePostDTO.setMode("mode error");
            }
        }
        return rePostDTO;
    }
}
