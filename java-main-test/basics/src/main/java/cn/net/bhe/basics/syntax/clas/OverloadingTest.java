package cn.net.bhe.basics.syntax.clas;

/**
 * 匹配时优先选类型最接近的
 */
public class OverloadingTest {

    public static void main(String[] args) {
        overloading(null, 1);
    }

    static void overloading(int i, int j) {
        System.err.println("int i, int j");
    }

    static void overloading(int i, long j) {
        System.err.println("int i, long j");
    }

    static void overloading(long i, int j) {
        System.err.println("long i, int j");
    }

    static void overloading(long i, long j) {
        System.err.println("long i, long j");
    }

    static void overloading(Object i, Object j) {
        System.err.println("Object i, Object j");
    }

}
