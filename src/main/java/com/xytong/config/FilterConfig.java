package com.xytong.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;
import java.util.List;


/**
 * @author bszydxh
 */
@Configuration
public class FilterConfig {
    @Bean//bean是比对象更高级的抽象“对象”，交由Spring进行生命周期管理，bean 是由 Spring IoC 容器实例化、组装和管理的“对象”
    public FilterRegistrationBean<?> filterRegistrationBean() {//强制加入Content-Length返回头
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new ShallowEtagHeaderFilter());
        filterBean.setUrlPatterns(List.of("*"));
        return filterBean;
    }
}
