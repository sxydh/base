package cn.net.bhe.springbootmybatis.bean;

import lombok.Data;

@Data
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

}
