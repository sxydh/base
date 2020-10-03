package fun.ehe.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fun.ehe.bean.Rt;
import fun.ehe.exception.BusinessException;
import fun.ehe.exception.ExpException;

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
        return rt;
    }

}
