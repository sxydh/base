package cn.net.bhe.basics.syntax.operator;

import org.junit.jupiter.api.Test;

/**
 * lambda表达式用于简化某些情况下的写法。
 * 符合{@link FunctionalInterface}的接口才能用于lambda表达式，即接口只能有一个抽象方法。
 */
public class LambdaTest {

    /**
     * 简化匿名类书写
     */
    @Test
    public void simplifiedAnonymous() {
        Interfac interfac = string -> System.out.println(string);
        interfac.method("lambda");
        
        // 相当于以下
        interfac = new Interfac() {
            @Override
            public void method(String string) {
                System.out.println(string);
            }
        };
        interfac.method("anonymous");
    }
}

interface Interfac {
    public void method(String arg); 
}