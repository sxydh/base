package fun.ehe.bean;

import java.io.Serializable;

import fun.ehe.utils.DateUtils;

public class Message implements Serializable {

    private static final long serialVersionUID = -9103358483165670689L;

    private String content;
    private String createtime;

    public Message(String content) {
        this.content = content;
        this.createtime = DateUtils.get();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getContent() {
        return content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}
