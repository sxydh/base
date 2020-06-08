package cn.net.bhe.basics.keep;

import org.junit.jupiter.api.Test;

/**
 * 获取当前方法名
 */
public class CurrentMethodName {

    @Test
    public void throwable() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println(nameofCurrMethod);
    }

    @Test
    public void innerObj() {
        String nameofCurrMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(nameofCurrMethod);
    }

    @Test
    public void thread() {
        String nameofCurrMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(nameofCurrMethod);
    }

}
