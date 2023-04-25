package com.xytong.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xytong.model.dto.ImageUploadResponseDTO;
import com.xytong.utils.NameUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class ImageUploadController {
    @Value("${file.image.path}")
    String imgPath;

    @RequestMapping("/image/upload")
    @ResponseBody
    public ImageUploadResponseDTO uploadImg(@RequestParam("image") MultipartFile multipartFile) {
        ImageUploadResponseDTO imageUploadResponseDTO = new ImageUploadResponseDTO();
        imageUploadResponseDTO.setTimestamp(System.currentTimeMillis());
        if (multipartFile.isEmpty()) {
            imageUploadResponseDTO.setMsg("multipartFile is empty");
        }
        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            imageUploadResponseDTO.setMsg("multipartFileName is empty");
        }

        Long id = IdWorker.getId();
        try {
            File file = new File(imgPath + id + NameUtils.getFileExtension(multipartFile.getOriginalFilename()));
            multipartFile.transferTo(file);
        } catch (IOException e) {
            imageUploadResponseDTO.setMsg("save error");
        }
        imageUploadResponseDTO.setMsg("save ok");
        imageUploadResponseDTO.setPath("/source/image/"+id + NameUtils.getFileExtension(multipartFile.getOriginalFilename()));
        return imageUploadResponseDTO;
    }
}
