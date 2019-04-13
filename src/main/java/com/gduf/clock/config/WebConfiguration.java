package com.gduf.clock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IDEA
 * author: HaoChen
 * Date: 2019/4/3
 * Time: 12:15
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Value("${web.upload.image.path}")
    private String imagePath;
    @Value("${web.upload.video.path}")
    private String videoPath;
    @Value("${web.upload.speech.path}")
    private String speechPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+imagePath);
        registry.addResourceHandler("/video/**").addResourceLocations("file:"+videoPath);
        registry.addResourceHandler("/speech/**").addResourceLocations("file:"+speechPath);
    }
}
