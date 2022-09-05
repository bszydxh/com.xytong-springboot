package com.xytong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan("com.xytong.data.mapper")
public class MainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
