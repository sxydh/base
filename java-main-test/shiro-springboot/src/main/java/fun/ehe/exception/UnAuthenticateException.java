package fun.ehe.exception;

public class UnAuthenticateException extends RuntimeException {

    private static final long serialVersionUID = -3241434517041131493L;

    public UnAuthenticateException(String msg) {
        super(msg);
    }

    public UnAuthenticateException() {
        super();
    }
}
