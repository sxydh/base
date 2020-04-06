package cn.net.bhe.basics.operator;

public class Lambda {

    /**
     * 用途一
     * 简化匿名类书写
     */
    public static void
    // simplifiedAnonymous() 
    main(String[] args)
    {
        Interfac interfac = string -> System.out.println(string);
        interfac.method("anonymous");
    }
}

interface Interfac {
    public void method(String arg); // 默认方法
    public default Integer extendMethod(String arg) { // default方法子类可以选择性实现，这避免了接口扩展时子类无法通过编译的问题
        return Integer.parseInt(arg);
    }
}