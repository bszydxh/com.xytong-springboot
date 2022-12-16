package com.xytong.utils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanCreateUtils implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    public static <T> T getBeanByClass(Class<T> clazz) {
        if (applicationContext == null) {
            log.error("applicationContext null!");
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        BeanCreateUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
