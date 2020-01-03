package cn.net.bhe.springbootmybatis.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7153342874978634242L;

    public BusinessException(String message) {
        super(message);
    }

}