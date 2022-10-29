package com.xytong.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class FileServiceTest {
    @Autowired
    FileService fileService;

    @Test
    public void fileReadTest() {
        log.info(fileService.readFile("classpath:access/rsa_token"));
        assertNotNull(fileService.readFile("classpath:access/rsa_token"));
    }
}
