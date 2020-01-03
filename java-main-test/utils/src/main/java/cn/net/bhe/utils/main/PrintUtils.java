package cn.net.bhe.utils.main;

public enum PrintUtils {
    ;

    public static void p(Object obj) {
        System.out.print(obj);
    }

    public static void pln(Object obj) {
        System.out.println(obj);
    }

    public static void pm(Object obj) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (int i = st.length - 1; i > 1; i--) {
            System.out.println(st[i].getClassName() + " -> " + st[i].getMethodName());
        }
        System.out.println(obj);
    }

}
