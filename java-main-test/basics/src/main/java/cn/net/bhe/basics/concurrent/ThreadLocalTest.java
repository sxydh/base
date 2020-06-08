package cn.net.bhe.basics.concurrent;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 每个线程都持有一个ThreadLocal.ThreadLocalMap，而set和get操作是由ThreadLocal实例来完成的。
 * 存取操作相当于先获取当前线程的ThreadLocalMap，然后再以ThreadLocal实例为键来存取目标值。
 * 
 * 如果全局共享一个ThreadLocal实例，不同的线程用该实例可存取自己的目标值，达到线程隔离的目的。
 * 适用场景：
 * 多线程共享一个全局引用来存取各自不同的目标值，为了保证线程安全，只能加synchronized。引入ThreadLocal后，各线程可以将各自的目标值存储在自己的ThreadLocalMap中，
 * 互不干扰，典型的空间换时间。
 */
public class ThreadLocalTest {
    static Logger log = LoggerFactory.getLogger(ThreadLocalTest.class);

    /**
     * 以ThreadLocal实例为键存储目标值
     */
    @Test
    public void set() {
        ThreadLocal<int[]> globalThreadLocal = new ThreadLocal<>();
        
        globalThreadLocal.set(new int[] { 1, 2, 3 }); // 主线程值
        new Thread() {
            @Override
            public void run() {
                globalThreadLocal.set(new int[] { 4, 5, 6 }); // 新线程值
                log.info(Arrays.toString(globalThreadLocal.get()));
            };
        }.start();
        log.info(Arrays.toString(globalThreadLocal.get()));
    }
}
