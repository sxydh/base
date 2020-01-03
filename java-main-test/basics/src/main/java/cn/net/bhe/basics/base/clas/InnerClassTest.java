package cn.net.bhe.basics.base.clas;

public class InnerClassTest {

    public static class StaticInner {
    }

    public class Inner {
    }

    public static void main(String[] args) {
        System.out.printf("%-15s%s%n", "static inner:", new StaticInner());
        System.out.printf("%-15s%s%n", "inner:", new InnerClassTest().new Inner());
    }
}

class Outer {

    public static void main(String[] args) {
        System.out.printf("%-15s%s%n", "static inner:", new InnerClassTest.StaticInner());
        System.out.printf("%-15s%s%n", "inner:", new InnerClassTest().new Inner());
    }
}
