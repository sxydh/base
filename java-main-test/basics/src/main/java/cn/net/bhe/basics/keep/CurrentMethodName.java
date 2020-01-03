package cn.net.bhe.basics.keep;

public class CurrentMethodName {

    public static void main(String[] args) {
        throwable();
        innerObj();
        thread();
    }

    public static void throwable() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

    public static void innerObj() {
        String nameofCurrMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

    public static void thread() {
        String nameofCurrMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

}
