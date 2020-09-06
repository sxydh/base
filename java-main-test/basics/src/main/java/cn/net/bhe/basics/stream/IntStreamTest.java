package cn.net.bhe.basics.stream;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import static cn.net.bhe.utils.main.PrintUtils.*;

public class IntStreamTest {

    /*
     * range
     * 
     * 类似于Python的range(int, int)
     */
    @Test
    public void t() {
        IntStream.range(0, 10).forEach((int i) -> {
            pln(i);
        });
    }

}
