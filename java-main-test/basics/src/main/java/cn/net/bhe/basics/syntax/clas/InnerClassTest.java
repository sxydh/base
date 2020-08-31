package cn.net.bhe.basics.syntax.clas;

import static cn.net.bhe.utils.main.PrintUtils.*;

/**
 * 内部类共有四种类型，即非静态内部类、静态内部类、局部内部类和匿名内部类。
 * ▪ 静态内部类，相当于类变量。
 * ▪ 非静态内部类，相当于实例变量。可直接调用外部类所有方法。
 * ▪ 局部内部类，作用域在method范围的内部类，只能是非静态的。
 * ▪ 匿名内部类，相当于实现接口/继承类的一种缩写方式。匿名内部类的方法参数必须是final的，而局部内部类则不用。
 * 
 * 特性：
 * ▪ 外部属性总是对内可见。
 */
public class InnerClassTest {

}

class Outer {
    private Object privateObj = new Object();
    private void privateMethod() {}

    // 静态内部类
    public static class StaticInnerClass {
        Object privateObj = new Outer().privateObj; // 即时是private的，外部属性对内依然可见
    }
    
    // 非静态内部类
    public class NonStaticInnerClass {
        void nonStaticInnerClass() {
            privateMethod(); 
        }
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // 局部内部类
        class LocalInnerClass {}
        // 匿名内部类
        new Object() {
            {
                pln(new Outer().privateObj + "");;
            }
        };
        
        // 对应的创建语法
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        
        NonStaticInnerClass nonStaticInnerClass = new Outer().new NonStaticInnerClass(); 
        // 另外一种声明
        // Outer.NonStaticInnerClass
        // 
        // 另外一种new
        // Outer outer = new Outer();
        // outer.new NonStaticInnerClass();
        
        LocalInnerClass localInnerClass = new LocalInnerClass();
    }
}

