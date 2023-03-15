package com.xytong.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void testValue() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("string", "I am a string");
        log.info(valueOperations.get("string").toString());
    }
    @Test
    void testHash() {
        redisTemplate.opsForHash().put("tests","123",true);
        redisTemplate.opsForHash().put("tests","12w3",123);
        redisTemplate.opsForHash().put("tests","12wassd3","asdasd");
        log.info(redisTemplate.opsForHash().hasKey("tests","123").toString());
    }
}
