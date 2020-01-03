package fun.ehe.utils;

public abstract class T {
    public static void t(Object s, Object... ts) {
        System.out.println("---" + s + "---");
        for (Object t : ts) {
            System.out.println("   > " + t);
        }
        System.out.println();
    }

    public synchronized static void s(Object o) {
        System.out.print(o);
    }

    public synchronized static void sln(Object o) {
        System.out.println(o);
    }
}
