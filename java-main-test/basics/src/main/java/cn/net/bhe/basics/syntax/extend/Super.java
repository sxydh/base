package cn.net.bhe.basics.syntax.extend;

public class Super {

    String data = "Super data";

    Super() {
        overrideMe();
    }

    public void overrideMe() {
        System.out.println("Call Super.overrideMe()");
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.toString();
    }

}
