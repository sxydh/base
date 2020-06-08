package cn.net.bhe.basics.io;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

/**
 * 参考https://www.jianshu.com/p/042107d5c810
 */
public class ByteBufferTest {
    
    @Test
    public void bytesToInt() {
        byte[] bytes = new byte[] {0, 0, 1, 0};
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        int num = wrap.getInt();
        System.out.println(num);
    }

}
