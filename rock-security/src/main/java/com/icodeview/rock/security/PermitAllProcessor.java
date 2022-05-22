package com.icodeview.rock.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class PermitAllProcessor {
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    public String[] process(){
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        ArrayList<String> urls =new ArrayList<>();
        for(Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            PermitAll annotation = getPermitAll(entry.getValue());
            if(annotation != null) {
                Set<String> patterns = entry.getKey().getPatternsCondition().getPatterns();
                urls.addAll(patterns);
            }
        }
        log.info("Permitted urls as follows: {}",
                StringUtils.collectionToDelimitedString(urls, ",", "[", "]"));
        return urls.toArray(new String[0]);
    }

    private PermitAll getPermitAll(HandlerMethod value) {
        PermitAll annotation = value.getMethodAnnotation(PermitAll.class);
        if(annotation == null){
            annotation = value.getBeanType().getAnnotation(PermitAll.class);
        }
        return annotation;
    }
}
