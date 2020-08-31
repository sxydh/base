package cn.net.bhe.basics.syntax.clas;

/**
 * ▪ 接口中每一个方法也是隐式抽象的，接口中的方法会被隐式的指定为 public abstract（只能是 public abstract，其他修饰符都会报错）。
 * ▪ 接口中可以含有变量，但是接口中的变量会被隐式的指定为 public static final 变量（并且只能是 public，用 private 修饰会报编译错误）。
 * ▪ 接口中的方法是不能在接口中实现的，只能由实现接口的类来实现接口中的方法。
 */
public interface InterfaceTest {
    
    int val = 10; // 即使没有public修饰，但因为默认是public的，所以其它包可以访问
    
    public void publi();
    void nonPublic();
    
    /*
     * 只能abstract、default、static三选一
     */
    public default void defaul() {
        
    }

}

interface NonPublicInterface {
    
}
