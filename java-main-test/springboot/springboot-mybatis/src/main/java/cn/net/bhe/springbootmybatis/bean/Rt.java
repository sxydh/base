package cn.net.bhe.springbootmybatis.bean;

public class Rt {

    public static final int CODE_SUC = 200;
    public static final int CODE_ERR_SYS = 300;
    public static final int CODE_ERR_BS = 301;
    public static final int CODE_ERR_EXP = 302;
    public static final int CODE_ERR_CRYPT = 303;

    private Integer sc;
    private String msg;
    private Object data;

    public static Rt instance() {
        return new Rt();
    }

    public Integer getSc() {
        return sc;
    }

    public void setSc(Integer sc) {
        this.sc = sc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
