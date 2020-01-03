package cn.net.bhe.springbootmybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.springbootmybatis.dict.K;
import cn.net.bhe.springbootmybatis.dict.Prop;
import cn.net.bhe.utils.main.AESUtils;
import cn.net.bhe.utils.main.JacksonUtils;

public class BaseController {

    static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected String toStr(HttpServletRequest request, Object obj) {
        String str = JacksonUtils.objToJsonStr(obj);
        request.setAttribute(K.RES_BODY.toString(), str);

        if (Prop.ENCRYPT_API) {
            try {
                str = AESUtils.encrypt(str, Prop.ENCRYPT_PWD);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
            request.setAttribute(K.RES_BODY_ENCRYPT.toString(), str);
        }

        return str;
    }

}
