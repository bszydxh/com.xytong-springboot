package com.xytong.controller;

import com.xytong.factory.BO2DTOFactory;
import com.xytong.model.bo.ShBO;
import com.xytong.model.dto.sh.ShAddResponseDTO;
import com.xytong.model.dto.sh.ShAddRequestDTO;
import com.xytong.model.dto.sh.ShGetResponseDTO;
import com.xytong.model.dto.sh.ShGetRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.ShService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author bszydxh
 */
@RestController
public class ShController {
    final ShService shService;
    final AccessService accessService;
    public static final String SH_MODULE_NAME = "secondhand";

    public ShController(ShService shService, AccessService accessService) {
        this.shService = shService;
        this.accessService = accessService;
    }

    @RequestMapping(value = "/secondhand", produces = "application/json")
    @ResponseBody
    public ShGetResponseDTO getShList(@RequestBody ShGetRequestDTO shGetRequestDTO) {
        if (!Objects.equals(shGetRequestDTO.getModule(), SH_MODULE_NAME)) {
            return BO2DTOFactory.getGetPostDTO("module error", ShGetResponseDTO.class);
        }
        Integer start = shGetRequestDTO.getNumStart();
        Integer end = shGetRequestDTO.getNumEnd();
        Integer num = shGetRequestDTO.getNeedNum();
        if (start == null || end == null || num == null) {
            return BO2DTOFactory.getGetPostDTO("interface error", ShGetResponseDTO.class);
        }
        if (start > end || end - start != num - 1) {
            return BO2DTOFactory.getGetPostDTO("num error", ShGetResponseDTO.class);
        } else {
            try {
                List<ShBO> shList = shService.getShList(
                        shGetRequestDTO.getMode(),
                        shGetRequestDTO.getTimestamp(),
                        start,
                        end);
                return BO2DTOFactory.getGetPostDTO(
                        shGetRequestDTO.getMode(),
                        shGetRequestDTO.getTimestamp(),
                        start,
                        start + shList.size() - 1,
                        shList,
                        ShGetResponseDTO.class);
            } catch (Exception e) {
                return BO2DTOFactory.getGetPostDTO("mode error", ShGetResponseDTO.class);
            }
        }
    }

    @RequestMapping(value = "/secondhand/v1/get", produces = "application/json")
    @ResponseBody
    public ShGetResponseDTO getShList2(@RequestBody ShGetRequestDTO shGetRequestDTO) {
        return getShList(shGetRequestDTO);
    }

    @RequestMapping(value = "/secondhand/v1/add", produces = "application/json")
    @ResponseBody
    public ShAddResponseDTO addShList(@RequestBody ShAddRequestDTO shAddRequestDTO) {
        ShAddResponseDTO shAddResponseDTO = new ShAddResponseDTO();
        shAddResponseDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
        if (!Objects.equals(shAddRequestDTO.getModule(), SH_MODULE_NAME)) {
            shAddResponseDTO.setMode("module error");
            return shAddResponseDTO;
        }
        if (!accessService.tokenCheckerWithUsername(shAddRequestDTO.getToken(), shAddRequestDTO.getUsername())) {
            shAddResponseDTO.setMode("token error");
            return shAddResponseDTO;
        }
        ShBO shBO = new ShBO();
        shBO.setPrice(shAddRequestDTO.getPrice());
        shBO.setText(shAddRequestDTO.getText());
        shBO.setTimestamp(System.currentTimeMillis());
        shBO.setTitle(shAddRequestDTO.getTitle());
        shBO.setUserName(shAddRequestDTO.getUsername());
        if (shService.addSh(shBO)) {
            shAddResponseDTO.setMode("add ok");
        } else {
            shAddResponseDTO.setMode("add error");
        }
        return shAddResponseDTO;
    }
}
