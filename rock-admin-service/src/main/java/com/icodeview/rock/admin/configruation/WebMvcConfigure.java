package com.icodeview.rock.admin.configruation;

import com.icodeview.rock.components.JacksonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    @Resource
    private JacksonHttpMessageConverter jacksonHttpMessageConverter;
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonHttpMessageConverter);
    }
}
