package cn.net.bhe.basics.io;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    
    public static
    void main(String[] args) 
//    int bytesToInt()
    {
        byte[] bytes = new byte[] {0, 0, 1, 0};
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        int num = wrap.getInt();
        System.out.println(num);
//        return num;
    }

}
