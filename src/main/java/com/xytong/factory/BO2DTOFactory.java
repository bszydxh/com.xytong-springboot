package com.xytong.factory;

import com.xytong.model.dto.BbsGetDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bszydxh
 */
@Slf4j
public class BO2DTOFactory {
    public static <T, S extends BbsGetDTO> S getBbsGetResponseDTO(String mode, Integer start, Integer end, List<T> list, Class<S> returnValueType) {
        S getPostDTO = null;
        try {
            getPostDTO = returnValueType.getConstructor(new Class[]{}).newInstance();
            getPostDTO.setData(list);
            getPostDTO.setMode(mode);
            getPostDTO.setNumStart(start);
            getPostDTO.setNeedNum(end - start + 1);
            getPostDTO.setNumEnd(end);
            getPostDTO.setTimestamp(System.currentTimeMillis());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return getPostDTO;
    }

    public static <T, S extends BbsGetDTO> S getBbsGetResponseDTO(String mode, Long timestamp, Integer start, Integer end, List<T> list, Class<S> returnValueType) {
        S getPostDTO = null;
        try {
            getPostDTO = returnValueType.getConstructor(new Class[]{}).newInstance();
            getPostDTO.setData(list);
            getPostDTO.setMode(mode);
            getPostDTO.setNumStart(start);
            getPostDTO.setNeedNum(end - start + 1);
            getPostDTO.setNumEnd(end);
            getPostDTO.setTimestamp(timestamp);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return getPostDTO;
    }

    public static <S extends BbsGetDTO> S getBbsGetResponseDTO(String mode, Class<S> cls) {
        S getPostDTO = null;
        try {
            getPostDTO = cls.getConstructor(new Class[]{}).newInstance();
            getPostDTO.setData(new ArrayList<>());
            getPostDTO.setMode(mode);
            getPostDTO.setNumStart(0);
            getPostDTO.setNeedNum(0);
            getPostDTO.setNumEnd(0);
            getPostDTO.setTimestamp(System.currentTimeMillis());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return getPostDTO;
    }
}
