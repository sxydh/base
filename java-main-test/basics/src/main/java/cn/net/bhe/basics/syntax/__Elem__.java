package cn.net.bhe.basics.syntax;

import org.junit.jupiter.api.Test;
import static cn.net.bhe.utils.main.PrintUtils.*;

public class __Elem__ {
    
    @Test
    // try catch finally
    // 若finally块没有覆盖return，则finally块的修改将无法体现
    public void tcf() {
        class TCF {
            int tcf() {
                int i = 0;
                try {
                    i = 1;
                    return i;
                } catch (Exception e) {
                    e.printStackTrace();
                    return i;
                } finally {
                    i = 2;
                }
            }
        }
        pln(new TCF().tcf()); // 1
    }
    
    @Test
    // TWR语法可以保证try块内打开的资源在程序结束前得以释放，前提是打开的资源对象必须实现
    // java.lang.AutoCloseable或java.io.Closeable
    // 
    // The try-with-resources statement is a try statement that declares one or more resources. 
    // A resource is an object that must be closed after the program is finished with it. The 
    // try-with-resources statement ensures that each resource is closed at the end of the 
    // statement. Any object that implements java.lang.AutoCloseable, which includes all objects 
    // which implement java.io.Closeable, can be used as a resource.
    public void twr() {
        
    }

}
