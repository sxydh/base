package fun.ehe.utils;

public abstract class MyStringUtils {

    public static void main(String[] args) {
        T.t(null, upper("InternalServerError"));
    }

    public static boolean notEmpty(Object t) {
        boolean flag = false;
        if (t != null && !t.toString().isEmpty()) {
            flag = true;
        }
        return flag;
    }

    public static String upper(String s) {
        return s.toUpperCase();
    }
}
