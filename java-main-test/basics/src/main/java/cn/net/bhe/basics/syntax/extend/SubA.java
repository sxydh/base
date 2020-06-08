package cn.net.bhe.basics.syntax.extend;

import java.util.Date;

public class SubA extends Super {
    String data = "SubA data";
    final Date date;

    SubA() {
        date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(data);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
