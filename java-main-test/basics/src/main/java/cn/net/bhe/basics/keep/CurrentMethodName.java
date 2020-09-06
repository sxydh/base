package cn.net.bhe.basics.keep;

import org.junit.jupiter.api.Test;
import static cn.net.bhe.utils.main.PrintUtils.*;

/**
 * 获取当前方法名
 */
public class CurrentMethodName {
    
    /*
     * getEnclosingMethod()，可以获取方法对象
     */
    @Test
    public void enclosingMethod() {
        pln(new Object() {
        }.getClass().getEnclosingMethod());
    }

    /*
     * 栈跟踪法getStackTrace()，只能获取方法名
     */
    @Test
    public void stackTrace() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        pln(nameofCurrMethod);
        
        nameofCurrMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        pln(nameofCurrMethod);
    }

}
