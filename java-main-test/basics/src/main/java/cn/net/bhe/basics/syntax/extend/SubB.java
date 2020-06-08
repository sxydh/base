package cn.net.bhe.basics.syntax.extend;

public class SubB extends Super {
    String data = "SubB data";

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

}
