package cn.net.bhe.springbootmybatis.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.net.bhe.springbootmybatis.filter.CORSFilter;
import cn.net.bhe.springbootmybatis.filter.DecryptFilter;
import cn.net.bhe.springbootmybatis.filter.GateFilter;
import cn.net.bhe.springbootmybatis.filter.PreprocessFilter;

@Configuration
public class FilterConfig {

    static final Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public FilterRegistrationBean<GateFilter> addGateFilter(GateFilter gateFilter) {
        FilterRegistrationBean<GateFilter> register = new FilterRegistrationBean<>();
        register.setFilter(gateFilter);
        register.setName(gateFilter.getClass().getSimpleName());
        register.setOrder(7);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        register.setUrlPatterns(urlPatterns);
        return register;
    }

    @Bean
    public FilterRegistrationBean<DecryptFilter> addDecryptFilter(DecryptFilter decryptFilter) {
        FilterRegistrationBean<DecryptFilter> register = new FilterRegistrationBean<>();
        register.setFilter(decryptFilter);
        register.setName(decryptFilter.getClass().getSimpleName());
        register.setOrder(8);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        register.setUrlPatterns(urlPatterns);
        return register;
    }

    @Bean
    public FilterRegistrationBean<CORSFilter> addCORSFilter(CORSFilter corsFilter) {
        FilterRegistrationBean<CORSFilter> register = new FilterRegistrationBean<>();
        register.setFilter(corsFilter);
        register.setName(corsFilter.getClass().getSimpleName());
        register.setOrder(9);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        register.setUrlPatterns(urlPatterns);
        return register;
    }

    @Bean
    public FilterRegistrationBean<PreprocessFilter> addPreprocessFilter(PreprocessFilter preprocessFilter) {
        FilterRegistrationBean<PreprocessFilter> register = new FilterRegistrationBean<>();
        register.setFilter(preprocessFilter);
        register.setName(preprocessFilter.getClass().getSimpleName());
        register.setOrder(10);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        register.setUrlPatterns(urlPatterns);
        return register;
    }

}
