package cn.net.bhe.basics.syntax.extend;
import static cn.net.bhe.utils.main.PrintUtils.*;

public class Super {

    String data = "super data";

    Super() {
        this.m1();
    }
    
    public void m1() {
        pln(new Object() {}.getClass().getEnclosingMethod());
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
