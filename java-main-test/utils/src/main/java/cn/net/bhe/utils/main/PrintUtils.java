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
    
    /**
     * 格式化输出
     * @param obj
     * @see <a href = "https://blog.csdn.net/lonely_fireworks/article/details/7962171">详情</a>
     * @return
     */
    public static String pf(String format, Object obj) {
        String ret = "";
        ret = String.format(format, obj);
//        ret = String.format("% 10d", 7); // 长度10，右对齐，不够补空格
//        ret = String.format("%10s", "7"); // 长度10，右对齐，不够补空格
        System.out.println(ret);
        return ret;
    }
    
    public static void main(String[] args) {
        pf("%06d", 333);
    }

}
