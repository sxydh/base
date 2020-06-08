package cn.net.bhe.basics.syntax.clas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 内部类共有四种类型，即非静态内部类、静态内部类、局部内部类和匿名内部类。
 * ▪ 静态内部类，相当于类变量
 * ▪ 非静态内部类，相当于实例变量
 * ▪ 局部内部类，作用域在method范围的内部类，只能是非静态的
 * ▪ 匿名内部类，相当于实现接口/继承类的一种缩写方式
 * 
 * 外部属性总是对内可见。
 */
public class InnerClass {
    static Logger log = LoggerFactory.getLogger(InnerClass.class);
    
    private Object privateObj = new Object();

    // 静态内部类
    public static class StaticInnerClass {
        Object privateObj = new InnerClass().privateObj; // 即时是private的，外部属性对内依然可见
    }
    
    // 非静态内部类
    public class NonStaticInnerClass {}
    
    public static void main(String[] args) {
        // 局部内部类
        class LocalInnerClass {}
        // 匿名内部类
        new Object() { { log.info(new InnerClass().privateObj + ""); } };
        
        // 对应的创建语法
        new StaticInnerClass();
        new InnerClass().new NonStaticInnerClass();
        new LocalInnerClass();
    }
}

