package com.xytong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author bszydxh
 * 图片映射config
 */
@Configuration
public class ImagePathConfig implements WebMvcConfigurer {
    @Value("${file.image.path}")
    private String filePath;
    /**
     * 显示相对地址
     */
    @Value("${file.image.path.relative}")
    private String fileRelativePath;

    /**
     * 注意仅能上传固定大小的文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileRelativePath).
                addResourceLocations("file:/" + filePath);
    }
}
