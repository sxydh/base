package cn.net.bhe.basics.jvm.classload.init;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
