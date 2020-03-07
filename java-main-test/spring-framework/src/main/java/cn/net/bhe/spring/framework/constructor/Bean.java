package cn.net.bhe.spring.framework.constructor;

public class Bean {

    public Bean() {
        System.out.printf("%s\r\n", "empty");
    }

    public Bean(Object obj, Integer inte) {
        System.out.printf("%s,%s\r\n", obj, inte);
    }

    public Bean(Integer inte1, Integer inte2) {
        System.out.printf("%s,%s\r\n", inte1, inte2);
    }

    public Bean(Object str) {
        System.out.printf("%s\r\n", str);
    }

}
