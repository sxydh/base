package cn.net.bhe.springbootmybatis.exception;

public class CryptException extends RuntimeException {

    private static final long serialVersionUID = -3020991406788361566L;

    public CryptException(String message) {
        super(message);
    }

}
