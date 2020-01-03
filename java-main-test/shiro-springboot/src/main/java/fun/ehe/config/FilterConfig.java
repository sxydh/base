package fun.ehe.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fun.ehe.filter.PreprocessFilter;

@Configuration
public class FilterConfig {

    static Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        PreprocessFilter preprocessFilter = new PreprocessFilter();
        filterRegistrationBean.setFilter(preprocessFilter);
        filterRegistrationBean.setName(preprocessFilter.getClass().getSimpleName());
        filterRegistrationBean.setOrder(1);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }

}
