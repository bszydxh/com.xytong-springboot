package com.xytong.serviceTest;


import com.xytong.mapper.ForumMapper;
import com.xytong.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class MainApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ForumMapper forumMapper;

    @Test
    public void testSelect() {
        System.out.println("----- selectAll method test ------");
    }

    @Test
    public void getSpringVersion() {
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }
}
