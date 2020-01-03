package fun.ehe.bean.response;

public class ResponseTemplate {

    private int rsc;
    private String msg;
    private Object data;

    public ResponseTemplate(int rsc, String msg, Object data) {
        this.rsc = rsc;
        this.msg = msg;
        this.data = data;
    }

    public int getRsc() {
        return rsc;
    }

    public void setRsc(int rsc) {
        this.rsc = rsc;
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
