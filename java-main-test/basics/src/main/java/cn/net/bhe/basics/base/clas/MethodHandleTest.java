package cn.net.bhe.basics.base.clas;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    static class Reveiver {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object reveiver = System.currentTimeMillis() % 2 == 0 ? System.out : new Reveiver();
        getPrintlnMH(reveiver).invokeExact("test suc");
    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        MethodType mt = MethodType.methodType(void.class, String.class);
        /*
         * Finds a method handle in the specified class that conforms to the
         * given method name, method type, and conforms to the calling
         * permission.
         */
        return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }
}