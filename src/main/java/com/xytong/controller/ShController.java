package com.xytong.controller;

import com.xytong.model.bo.ShBO;
import com.xytong.model.dto.ShPostDTO;
import com.xytong.model.dto.ShRequestDTO;
import com.xytong.service.SecondhandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ShController {
    final SecondhandService shService;

    public ShController(SecondhandService shService) {
        this.shService = shService;
    }

    @RequestMapping(value = "/secondhand", produces = "application/json")
    public ShPostDTO shData(@RequestBody ShRequestDTO shRequestDTO) {
        ShPostDTO shPostDTO = new ShPostDTO();
        if (!Objects.equals(shRequestDTO.getModule(), "secondhand")) {
            shPostDTO.setMode("module error");
            return shPostDTO;
        }

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
            try {
                List<ShBO> shList = shService.getShList(shRequestDTO.getMode(), start, end);
                shPostDTO.setShData(shList);
            } catch (Exception e) {
                shPostDTO.setMode("mode error");
            }
        }
        return shPostDTO;
    }
}
