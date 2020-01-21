package cn.net.bhe.basics.base.lambda;

public class Main {

    /**
     * 用途一
     * 简化匿名类书写，但匿名类继承的接口必须带@FunctionalInterface
     */
    public static void
    // simplifiedAnonymous() 
    main(String[] args)
    {
        FunctionalInterfac func = string -> System.out.println(string);
        /* 以上相当于
        new FunctionalInterfac() {
            @Override
            public void method(String arg) {
                System.out.println(arg);
            }
        };
        */
        func.method("anonymous");
    }
}

@FunctionalInterface
interface FunctionalInterfac {
    public void method(String arg); // 默认方法
    public default Integer extendMethod(String arg) { // default方法子类可以选择性实现，这避免了接口扩展时子类无法通过编译的问题
        return Integer.parseInt(arg);
    }
}
