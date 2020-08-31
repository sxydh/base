package cn.net.bhe.basics.reflect;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.lang.reflect.Method;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import cn.net.bhe.utils.main.DateUtils;

public class __Elem__ {
    
    /*
     * 实例化
     */
    @Test
    public void instantiate() {
        try {
//            // 只支持public的构造器
//            O o1 = O.class.getConstructor().newInstance(); 
//            pln(o1);
            // 支持非private的构造器
            @SuppressWarnings("deprecation")
            O o2 = O.class.newInstance();
            pln(o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void invoke() {
        try {
            O o = new O();
            Method[] methods = o.getClass().getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                method.invoke(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

@SuppressWarnings("unused")
class O {
    O() {};
    private int val = 100;
    private void pd() {
        pln(DateUtils.get());
    }
    static void puuid() {
        pln(UUID.randomUUID().toString());
    }
}
