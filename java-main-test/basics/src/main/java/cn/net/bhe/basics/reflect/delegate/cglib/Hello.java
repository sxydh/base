package cn.net.bhe.basics.reflect.delegate.cglib;

import static cn.net.bhe.utils.main.PrintUtils.*;

public class Hello {
    
    public String sayHello(String str) {
        pln(this);
        str = "Hello ".concat(str).concat("!");
        pln(str);
        return  str;
    }
    
}
