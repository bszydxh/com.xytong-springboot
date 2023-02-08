package com.xytong.controller;

import com.xytong.factory.BO2DTOFactory;
import com.xytong.model.bo.ReBO;
import com.xytong.model.dto.re.ReAddResponseDTO;
import com.xytong.model.dto.re.ReAddRequestDTO;
import com.xytong.model.dto.re.ReGetResponseDTO;
import com.xytong.model.dto.re.ReGetRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.ReService;
import com.xytong.service.UserService;
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
public class ReController {
    final UserService userService;
    final ReService reService;
    final AccessService accessService;
    public static final String RE_MODULE_NAME = "run_errands";

    public ReController(UserService userService, ReService reService, AccessService accessService) {
        this.userService = userService;
        this.reService = reService;
        this.accessService = accessService;
    }

    @RequestMapping(value = "/"+RE_MODULE_NAME, produces = "application/json")
    @ResponseBody
    public ReGetResponseDTO getReList( @RequestBody ReGetRequestDTO reGetRequestDTO) {
        if (!Objects.equals(reGetRequestDTO.getModule(), RE_MODULE_NAME)) {
            return BO2DTOFactory.getBbsGetResponseDTO("module error", ReGetResponseDTO.class);
        }
        Integer start = reGetRequestDTO.getNumStart();
        Integer end = reGetRequestDTO.getNumEnd();
        Integer num = reGetRequestDTO.getNeedNum();
        if (start == null || end == null || num == null) {
            return BO2DTOFactory.getBbsGetResponseDTO("interface error", ReGetResponseDTO.class);
        }
        if (start > end || end - start != num - 1) {
            return BO2DTOFactory.getBbsGetResponseDTO("num error", ReGetResponseDTO.class);
        } else {
            try {
                List<ReBO> reList = reService.getReList(
                        reGetRequestDTO.getMode(),
                        reGetRequestDTO.getTimestamp(),
                        start,
                        end);
                return BO2DTOFactory.getBbsGetResponseDTO(
                        reGetRequestDTO.getMode(),
                        reGetRequestDTO.getTimestamp(),
                        start,
                        start + reList.size() - 1,
                        reList,
                        ReGetResponseDTO.class);
            } catch (Exception e) {
                return BO2DTOFactory.getBbsGetResponseDTO("mode error", ReGetResponseDTO.class);
            }
        }
    }

    @RequestMapping(value = "/"+RE_MODULE_NAME+"/v1/get", produces = "application/json")
    @ResponseBody
    public ReGetResponseDTO getReList2(@RequestBody ReGetRequestDTO reGetRequestDTO) {
        return getReList(reGetRequestDTO);
    }

    @RequestMapping(value = "/"+RE_MODULE_NAME+"/v1/add", produces = "application/json")
    @ResponseBody
    public ReAddResponseDTO addReList(@RequestBody ReAddRequestDTO reAddRequestDTO) {
        ReAddResponseDTO reAddResponseDTO = new ReAddResponseDTO();
        reAddResponseDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
        if (!Objects.equals(reAddRequestDTO.getModule(), RE_MODULE_NAME)) {
            reAddResponseDTO.setMode("module error");
            return reAddResponseDTO;
        }
        if (!accessService.tokenCheckerWithUsername(reAddRequestDTO.getToken(), reAddRequestDTO.getUsername())) {
            reAddResponseDTO.setMode("token error");
            return reAddResponseDTO;
        }
        ReBO reBO = new ReBO();
        reBO.setPrice(reAddRequestDTO.getPrice());
        reBO.setText(reAddRequestDTO.getText());
        reBO.setTimestamp(System.currentTimeMillis());
        reBO.setTitle(reAddRequestDTO.getTitle());
        reBO.setUserName(reAddRequestDTO.getUsername());
        if (reService.addRe(reBO)) {
            reAddResponseDTO.setMode("add ok");
        } else {
            reAddResponseDTO.setMode("add error");
        }
        return reAddResponseDTO;
    }
}
