package cn.net.bhe.springbootmybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.net.bhe.springbootmybatis.bean.Rt;
import cn.net.bhe.springbootmybatis.dict.K;
import cn.net.bhe.springbootmybatis.exception.BusinessException;
import cn.net.bhe.springbootmybatis.exception.ExpException;
import cn.net.bhe.utils.main.JacksonUtils;

@RestController
@ControllerAdvice
public class ExceptionController {

    static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public Rt error(HttpServletRequest request, Exception e) {
        e = e == null ? new RuntimeException("unknown error") : e;
        
        Rt rt = new Rt();
        rt.setMsg(e.getLocalizedMessage());

        if (e instanceof BusinessException) {
            rt.setSc(Rt.CODE_ERR_BS);
        } else if (e instanceof ExpException) {
            rt.setSc(Rt.CODE_ERR_EXP);
        } else {
            rt.setSc(Rt.CODE_ERR_SYS);
            LOGGER.error("", e);
        }
        
        request.setAttribute(K.RES_BODY.toString(), JacksonUtils.objToJsonStr(rt));
        return rt;
    }

}
