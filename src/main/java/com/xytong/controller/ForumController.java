package com.xytong.controller;

import com.xytong.factory.BO2DTOFactory;
import com.xytong.model.bo.ForumBO;
import com.xytong.model.dto.forum.ForumAddResponseDTO;
import com.xytong.model.dto.forum.ForumAddRequestDTO;
import com.xytong.model.dto.forum.ForumGetResponseDTO;
import com.xytong.model.dto.forum.ForumGetRequestDTO;
import com.xytong.service.AccessService;
import com.xytong.service.ForumService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ForumController {

    public ForumController(ForumService forumService, AccessService accessService) {
        this.forumService = forumService;
        this.accessService = accessService;
    }

    final ForumService forumService;

    final AccessService accessService;

    public static final String FORUM_MODULE_NAME = "forums";

    @RequestMapping(value = "/" + FORUM_MODULE_NAME, produces = "application/json")
    @ResponseBody
    public ForumGetResponseDTO getForumList(@RequestBody ForumGetRequestDTO forumGetRequestDTO) {
        if (!Objects.equals(forumGetRequestDTO.getModule(), FORUM_MODULE_NAME)) {
            return BO2DTOFactory.getBbsGetResponseDTO("module error", ForumGetResponseDTO.class);
        }
        Integer start = forumGetRequestDTO.getNumStart();
        Integer end = forumGetRequestDTO.getNumEnd();
        Integer num = forumGetRequestDTO.getNeedNum();
        if (start == null || end == null || num == null) {
            return BO2DTOFactory.getBbsGetResponseDTO("interface error", ForumGetResponseDTO.class);
        }
        if (start > end || end - start != num - 1) {
            return BO2DTOFactory.getBbsGetResponseDTO("num error", ForumGetResponseDTO.class);
        } else {
            try {
                List<ForumBO> forumList = forumService.getForumList(
                        forumGetRequestDTO.getMode(),
                        forumGetRequestDTO.getTimestamp(),
                        start,
                        end);
                return BO2DTOFactory.getBbsGetResponseDTO(
                        forumGetRequestDTO.getMode(),
                        forumGetRequestDTO.getTimestamp(),
                        start,
                        start + forumList.size() - 1,
                        forumList,
                        ForumGetResponseDTO.class);
            } catch (Exception e) {
                return BO2DTOFactory.getBbsGetResponseDTO("mode error", ForumGetResponseDTO.class);
            }
        }
    }

    @RequestMapping(value = "/"+FORUM_MODULE_NAME+"/v1/get", produces = "application/json")
    @ResponseBody
    public ForumGetResponseDTO getForumList2(@RequestBody ForumGetRequestDTO forumGetRequestDTO) {
        return getForumList(forumGetRequestDTO);
    }

    @RequestMapping(value = "/"+FORUM_MODULE_NAME+"/v1/add", produces = "application/json")
    @ResponseBody
    public ForumAddResponseDTO addForumList(@RequestBody ForumAddRequestDTO forumAddRequestDTO) {
        ForumAddResponseDTO forumAddResponseDTO = new ForumAddResponseDTO();
        forumAddResponseDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
        if (!Objects.equals(forumAddRequestDTO.getModule(), FORUM_MODULE_NAME)) {
            forumAddResponseDTO.setMode("module error");
            return forumAddResponseDTO;
        }
        if (!accessService.tokenCheckerWithUsername(forumAddRequestDTO.getToken(), forumAddRequestDTO.getUsername())) {
            forumAddResponseDTO.setMode("token error");
            return forumAddResponseDTO;
        }
        ForumBO forumBO = new ForumBO();
        forumBO.setLikes(0);
        forumBO.setComments(0);
        forumBO.setForwarding(0);
        forumBO.setText(forumAddRequestDTO.getText());
        forumBO.setTimestamp(System.currentTimeMillis());
        forumBO.setTitle(forumAddRequestDTO.getTitle());
        forumBO.setUserName(forumAddRequestDTO.getUsername());
        if (forumService.addForum(forumBO)) {
            forumAddResponseDTO.setMode("add ok");
        } else {
            forumAddResponseDTO.setMode("add error");
        }
        return forumAddResponseDTO;
    }
}
