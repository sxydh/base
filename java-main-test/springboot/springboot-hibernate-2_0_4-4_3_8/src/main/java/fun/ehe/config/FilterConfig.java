package fun.ehe.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fun.ehe.filter.CORSFilter;
import fun.ehe.filter.PreFilter;

@Configuration
public class FilterConfig {

    static final Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public FilterRegistrationBean<CORSFilter> addCORSFilter(CORSFilter corsFilter) {
        FilterRegistrationBean<CORSFilter> register = new FilterRegistrationBean<>();
        register.setFilter(corsFilter);
        register.setName(corsFilter.getClass().getSimpleName());
        register.setOrder(1);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        register.setUrlPatterns(urlPatterns);
        return register;
    }

    @Bean
    public FilterRegistrationBean<PreFilter> addPreFilter(PreFilter preFilter) {
        FilterRegistrationBean<PreFilter> register = new FilterRegistrationBean<>();
        register.setFilter(preFilter);
        register.setName(preFilter.getClass().getSimpleName());
        register.setOrder(2);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        register.setUrlPatterns(urlPatterns);
        return register;
    }

}
