package cn.net.bhe.basics.syntax.extend;

/**
 * ▪ 只能单一继承，可以多重实现
 * ▪ private，final修饰的不可继承，构造函数不可继承
 * ▪ 静态方法可以继承，但是子类覆盖时不能用override修饰
 * 
 * ▪ 总是优先调用子类override的方法。所以不要尝试在构造器内调用可被override的方法，否则调用的将是子类override的方法
 * ▪ 方法总是优先使用所处实例的属性，不存在时才会使用继承的属性
 */
public class __App__ {
    public static void main(String[] args) {
        SubA subA = new SubA(); // null，父类构造器内部调用了可被override的方法，此时子类覆盖的实现返回了null
        SubB subB = new SubB(); // Call Super.overrideMe()，子类没有覆盖实现，这是构建父类实例时的正常输出
        System.out.println(subA.getData()); // Super data
        System.out.println(subB.getData()); // SubB data
    }

}
