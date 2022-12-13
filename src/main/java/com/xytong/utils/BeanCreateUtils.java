package com.xytong.utils;

import org.springframework.context.ConfigurableApplicationContext;

public class BeanCreateUtils {
    public static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        BeanCreateUtils.applicationContext = applicationContext;
    }

    public static <T> T getBeanByClass(Class<T> clazz) {
       return applicationContext.getBean(clazz);
    }
}
