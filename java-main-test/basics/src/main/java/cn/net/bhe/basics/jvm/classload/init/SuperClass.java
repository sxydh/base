package cn.net.bhe.basics.jvm.classload.init;

public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
    public static final String HELLO_WORLD = "hello world";
}
