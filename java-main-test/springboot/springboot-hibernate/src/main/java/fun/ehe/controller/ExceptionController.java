package fun.ehe.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import fun.ehe.bean.Rt;
import fun.ehe.exception.BusinessException;
import fun.ehe.exception.ExpException;

/**
 * The principle of this method is HttpServlet error redirection, the status code returned will be 500.
 */
@RestController
public class ExceptionController implements ErrorController {

    static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    private ErrorAttributes errorAttributes;

    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public Rt error(HttpServletRequest request) {
        Throwable e = getException(request);
        e = e == null ? new RuntimeException("unknown error") : e;

        Rt rt = new Rt();
        rt.setMsg(e.getLocalizedMessage());
        LOGGER.info(e.getLocalizedMessage());

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

    private Throwable getException(HttpServletRequest request) {
        return this.errorAttributes.getError(new ServletWebRequest(request));
    }

    @Override
    public String getErrorPath() {
        return "";
    }

}
