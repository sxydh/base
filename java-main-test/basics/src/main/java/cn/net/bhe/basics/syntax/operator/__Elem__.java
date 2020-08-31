package cn.net.bhe.basics.syntax.operator;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static cn.net.bhe.utils.main.PrintUtils.*;

public class __Elem__ {
    static final Logger log = LoggerFactory.getLogger(__Elem__.class);
    
    /*
     * 位合并
     */
    @Test
    public void bitNot() {
        // 位与
        pln(5 & 3); // output: 1, 0101 & 0011 = 0001
        // 还可以作逻辑与，但是没有短路操作
        int i = 0;
        pln(false & (i = 2) > 1); // output: false
        pln(i); // output: 2
        
        // 位或
        pln(5 | 3); // output: 7, 0101 | 0011 = 0111
        
        // 位异或，相同为0，相反为1
        pln(5 ^ 3); // output: 6, 0101 ^ 0011 = 0110
    }
    
    /*
     * 变位
     */
    @Test
    public void bitwise() {
        // 位非
        pln(~5); // output: 4, ~ 0000 0000 0000 0000 0000 0000 0000 0101 = 1111 1111 1111 1111 1111 1111 1111 1010
        
        // 带符号移位
        pln(8 >> 1); // output: 4
        pln(8 << 1); // output: 16
        
        // 无符号移位，只能右移，空位补0
        pln(8 >>> 1); // output: 4
    }
    
    /*
     * 自增
     */
    @Test
    public void add() {
        short i = 1;
        // i = i + 1; 无法编译
        i += 1; // 可以编译
        log.info("{}", i);
    }
    
    /*
     * 标签
     */
    @Test
    public void label() {
        while (true) {
            pln("outer", 1);
            outer: while (true) {
                pln("inner first", 1);
                while (true) {
                    pln("inner second", 1);
                    while (true) {
                        pln("inner third", 1);
                        break outer; // 中断outer包含的循环，outer外的循环继续
//                        continue outer; // continue outer下的最外层循环
                    }
                }
            }
        }
    }
    
}
