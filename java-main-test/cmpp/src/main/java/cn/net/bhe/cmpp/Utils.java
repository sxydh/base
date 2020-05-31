package cn.net.bhe.cmpp;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    
    static Logger log = LoggerFactory.getLogger(Utils.class);

    public static void sleep(TimeUnit timeUnit, int timeout) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("", e);
            Thread.currentThread().interrupt();
        }
    }
    
    public static void wait (Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
            log.error("", e);
            Thread.currentThread().interrupt();
        }
    }
    
    static Integer sequenceId = 0;
    public static int getSequenceId() {
        synchronized (sequenceId) {
            if (sequenceId > 0x7fffffff) {
                sequenceId = 0;
            } 
            return ++sequenceId;
        }
    }
    
    public static byte[] int24byteArray(int n) {
        byte b[] = new byte[4];
        b[0] = (byte) (n >> 24);
        b[1] = (byte) (n >> 16);
        b[2] = (byte) (n >> 8);
        b[3] = (byte) n;
        return b;
    }
    
    public static void main(String[] args) {
        log.info(Arrays.toString(int24byteArray(3)));
    }
    
}
