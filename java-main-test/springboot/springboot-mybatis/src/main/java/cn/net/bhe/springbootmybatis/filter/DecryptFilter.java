package cn.net.bhe.springbootmybatis.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.net.bhe.springbootmybatis.bean.Rt;
import cn.net.bhe.springbootmybatis.dict.K;
import cn.net.bhe.springbootmybatis.dict.Prop;
import cn.net.bhe.springbootmybatis.servlet.RequestWrapper;
import cn.net.bhe.utils.main.DESUtils;
import cn.net.bhe.utils.main.JacksonUtils;

@Component
public class DecryptFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (Prop.ENCRYPT_API) {
            RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
            String body = requestWrapper.getBody();

            try {

                String decrypt = DESUtils.decrypt(body, Prop.ENCRYPT_PWD);

                RequestWrapper deRequestWrapper = new RequestWrapper(requestWrapper, decrypt);
                chain.doFilter(deRequestWrapper, response);

            } catch (Exception ede) {
                Rt rt = Rt.instance();
                rt.setSc(Rt.CODE_ERR_CRYPT);
                rt.setMsg(ede.getLocalizedMessage());

                String resbody = JacksonUtils.objToJsonStr(rt);
                String enresbody = null;
                try {
                    enresbody = DESUtils.encrypt(resbody, Prop.ENCRYPT_PWD);
                } catch (Exception een) {
                    rt.setSc(Rt.CODE_ERR_SYS);
                    rt.setMsg(een.getLocalizedMessage());
                    resbody = JacksonUtils.objToJsonStr(rt);
                }

                request.setAttribute(K.RES_BODY.toString(), resbody);
                request.setAttribute(K.RES_BODY_ENCRYPT.toString(), enresbody);

                PrintWriter pw = response.getWriter();
                pw.write(StringUtils.isNotEmpty(enresbody) ? enresbody : resbody);
                pw.flush();
                pw.close();
            }
        }

        else {
            chain.doFilter(request, response);
        }
    }

}
