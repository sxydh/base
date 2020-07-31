package cn.net.bhe.springbootmybatis.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import cn.net.bhe.springbootmybatis.dict.Const;
import cn.net.bhe.springbootmybatis.dict.K;
import cn.net.bhe.springbootmybatis.servlet.RequestWrapper;
import cn.net.bhe.utils.main.JacksonUtils;

@Component
public class GateFilter implements Filter {

    static final Logger LOGGER = LoggerFactory.getLogger(GateFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
        long start = System.currentTimeMillis();
        String uri = requestWrapper.getRequestURI();
        String body = requestWrapper.getBody();
        String content = ""
                + "start at: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
                + "\r\nuri: " + uri
                + "\r\nrequest body:\r\n" + JacksonUtils.pretty(body);
        LOGGER.info(Const.LOGGER_FORMAT_INFO, content);

        try {
            chain.doFilter(requestWrapper, response);
        } catch (IOException | ServletException e) {
            LOGGER.error("", e);
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            String resbody = (String) requestWrapper.getAttribute(K.RES_BODY.toString());
            String enresbody = (String) requestWrapper.getAttribute(K.RES_BODY_ENCRYPT.toString());
            long spend = end - start;
            content = ""
                    + "end at: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
                    + "\r\nuri: " + uri
                    + "\r\nspend: " + spend
                    + "\r\nresponse body:\r\n" + JacksonUtils.pretty(resbody)
                    + "\r\nencrypt body: " + enresbody;
            LOGGER.info(Const.LOGGER_FORMAT_INFO, content);
        }
    }

}
