package cn.net.bhe.basics.syntax.extend;

import java.util.Date;
import static cn.net.bhe.utils.main.PrintUtils.*;

public class SubA extends Super {
    String data = "suba data";
    final Date date;

    SubA() {
        date = new Date();
    }

    @Override
    public void m1() {
        pln(new Object() {}.getClass().getEnclosingMethod());
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
