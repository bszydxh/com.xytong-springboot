package com.xytong.controller;

import com.xytong.model.BO.ShBO;
import com.xytong.model.DTO.ShPostDTO;
import com.xytong.model.DTO.ShRequestDTO;
import com.xytong.model.PO.ShPO;
import com.xytong.mapper.ShMapper;
import com.xytong.mapper.UserMapper;
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
    public ShPostDTO shData(@RequestBody ShRequestDTO shRequestDTO) {
        ShPostDTO shPostDTO = new ShPostDTO();
        if (!Objects.equals(shRequestDTO.getModule(), "secondhand")) {
            shPostDTO.setMode("module error");
            return shPostDTO;
        }
        switch (shRequestDTO.getMode()) {
            case ("newest"): {
                int start = shRequestDTO.getNumStart();
                int end = shRequestDTO.getNumEnd();
                int num = shRequestDTO.getNeedNum();
                if (start > end || end - start != num - 1) {
                    shPostDTO.setMode("num error");
                } else {
                    shPostDTO.setMode(shRequestDTO.getMode());
                    shPostDTO.setNumStart(start);
                    shPostDTO.setNeedNum(num);
                    shPostDTO.setNumEnd(end);
                    shPostDTO.setTimestamp(System.currentTimeMillis());
                    List<ShBO> shList = new ArrayList<>();
                    List<ShPO> shPOList = shMapper.selectList(null);
                    for (ShPO shPO : shPOList) {
                        int uid = shPO.getUserFkey();
                        shList.add(shPO.toShDataWithUserData(userMapper.selectById(uid)));
                    }
                    shPostDTO.setShData(shList);
                }
                break;
            }
            default: {
                shPostDTO.setMode("mode error");
            }
        }


        return shPostDTO;
    }
}
