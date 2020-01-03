package cn.net.bhe.basics.base.doublecolonoperator;

import java.util.Arrays;
import java.util.Date;

/**
 * The double colon (::) operator, also known as method reference operator in
 * Java, is used to call a method by referring to it with the help of its class
 * directly. They behave exactly as the lambda expressions. The only difference
 * it has from lambda expressions is that this uses direct reference to the
 * method by name instead of providing a delegate to the method.
 * 
 * 一般情况下就是将引用填充到接口的默认方法里面去（接口必须带@FunctionalInterface）
 */
public class Main {

    /**
     * Reference A Static Method
     */
    public static void staticMethod
    (String[] args) {
        Arrays.asList(new String[] { "white", "black" })
        .forEach(Utils::print);
    }
    
    /**
     * Reference An Instance Method of an Existing Object
     */
    public static void instanceMethod
    (String[] args) {
        Arrays.asList(new String[] { "white", "black" })
        .forEach(System.out::println);
    }
    
    /**
     * Reference An Instance Method of an Arbitrary Object of a Particular Type
     */
    public static void instanceMethodOfParticularType
    (String[] args) {
        Arrays.asList(new Utils[] { new Utils("white"), new Utils("black") })
        .forEach(Utils::toString);
    }
    
    /**
     * Reference Constructor
     */
    public static void main
    (String[] args) {
        InterfaceUtils<String> interfac = Utils::new; // 如果构造器有更多的参数，InterfaceUtils添加泛型个数即可
        interfac.create("value").toString();
    }

}

class Utils {

    private String value;
    
    Utils(String value) {
        this.value = value;
    }
    
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public String toString() {
        System.out.println(value + ", " + new Date());
        return value + ", " + new Date();
    }
}

@FunctionalInterface
interface InterfaceUtils<T> {
    Utils create(T t);
}
