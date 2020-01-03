package fun.ehe.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fun.ehe.bean.response.ResponseTemplate;
import fun.ehe.exception.UnAuthenticateException;

@RestControllerAdvice
public class ExceptionController {

    // Capturing shiro's exception
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseTemplate shiroException(ShiroException e) {
        return new ResponseTemplate(401, e.getMessage(), null);
    }

    // Capturing unAuthenticateException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthenticateException.class)
    public ResponseTemplate unAuthenticateException() {
        return new ResponseTemplate(401, "UnAuthenticate", null);
    }

    // Capturing all other exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseTemplate exception(HttpServletRequest request, Throwable e) {
        return new ResponseTemplate(getStatus(request).value(), e.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
