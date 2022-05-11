package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {

    @Autowired
    private JwtFilter jwtTokenFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(jwtTokenFilter);
        bean.addUrlPatterns("/profile/adm/*");
        bean.addUrlPatterns("/articleType/adm/*");
        bean.addUrlPatterns("/category/adm/*");
        bean.addUrlPatterns("/attach/adm/*");
        return bean;
    }

}
