package cn.net.bhe.basics.algorithm;
import static cn.net.bhe.utils.main.PrintUtils.*;

import org.junit.jupiter.api.Test;

public class MathTest {
    
    /*
     * 向下取整
     */
    @Test
    public void floor() {
        pln(Math.floor(8.5)); // 8.0
        pln(Math.floor(-8.5)); // -9.0
    }
    
    /*
     * 原值+0.5再floor
     */
    @Test
    public void round() {
        pln(Math.round(11.5)); // 12
        pln(Math.round(-11.5)); // -11
        pln(Math.round(-11.6)); // -12
    }
    
}
