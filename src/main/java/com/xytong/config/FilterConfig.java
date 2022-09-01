package com.xytong.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<?> filterRegistrationBean() {//强制加入Content-Length返回头
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new ShallowEtagHeaderFilter());
        filterBean.setUrlPatterns(List.of("*"));
        return filterBean;
    }

}
