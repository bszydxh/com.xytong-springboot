package com.xytong.service.impl;

import com.xytong.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
public class FileServiceImpl implements FileService {
    final
    ResourceLoader resourceLoader;

    public FileServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readFile(String path) {
        Resource resource = resourceLoader.getResource(path);
        String str = "";
        try {
            File key_file = resource.getFile();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(key_file));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int result = bufferedInputStream.read(); result != -1; result = bufferedInputStream.read()) {
                byteArrayOutputStream.write((byte) result);
            }
            str = byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
