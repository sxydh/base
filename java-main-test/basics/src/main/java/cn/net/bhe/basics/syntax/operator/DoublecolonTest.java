package cn.net.bhe.basics.syntax.operator;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 双冒号操作符，可以在某些情况下简化对方法的引用。
 * 使用双冒号操作符的地方都有一些限制条件，比如引用方的参数类型必须符合{@link FunctionalInterface}。
 * 
 * 例forEach(Printer::staticPrint)，forEach是引用方，forEach(Consumer<? super T> action)的参数类型
 * Consumer是符合{@link FunctionalInterface}的。
 */
public class DoublecolonTest {

    /**
     * 引用静态方法
     */
    @Test
    public void refStaticMethod() {
        Arrays.asList(new String[] { "white", "black" })
        .forEach(Printer::staticPrint);
    }
    
    /**
     * 引用实例方法
     */
    @Test
    public void refInstanceMethod1() {
        Arrays.asList(new String[] { "white", "black" })
        .forEach(new Printer()::print);
    }
    
    /**
     * 引用实例方法
     */
    @Test
    public void refInstanceMethod2() {
        Arrays.asList(new Printer[] { new Printer("white"), new Printer("black") })
        .forEach(Printer::toString);
    }
    
    /**
     * 引用构造器
     */
    @Test
    public void refConstructor() {
        // {@link ConstructPrinter}必须符合{@link FunctionalInterface}，即只能有一个抽象方法
        // 如果构造器有更多的参数，{@link ConstructPrinter}添加泛型个数即可
        ConstructPrinter<String> constructPrinter = Printer::new; 
        constructPrinter.create(new Random().nextInt() + "").toString();
    }

}

class Printer {

    private String value;
    
    Printer() {}
    
    Printer(String value) {
        this.value = value;
    }
    
    public void print(Object obj) {
        System.out.println(obj);
    }
    
    public static void staticPrint(Object obj) {
        System.out.println(obj);
    }

    @Override
    public String toString() {
        System.out.println(value);
        return value;
    }
}

interface ConstructPrinter<T> {
    Printer create(T t);
}
