package cn.net.bhe.basics.syntax.extend;

public class SubB extends Super {
    String data = "subb data";

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

}
