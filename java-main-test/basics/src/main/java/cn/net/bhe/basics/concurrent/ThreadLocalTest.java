package cn.net.bhe.basics.concurrent;

import java.util.Arrays;

/**
 * 每个线程都持有一个{@link ThreadLocal.ThreadLocalMap}，键是ThreadLocalMap实例，而set和get操作是由ThreadLocal实例来完成的。<br/>
 * 存取操作相当于先获取当前线程的ThreadLocalMap，然后再以ThreadLocal实例为键来存取目标值。<br/>
 * <p/>
 * 如果全局共享一个ThreadLocal实例，不同的线程用该实例可存取自己的目标值，达到线程隔离的目的。
 * <p/>
 * 适用场景：<br/>
 * 多线程共享一个全局引用来存取各自不同的目标值，为了保证线程安全，只能加synchronized。引入ThreadLocal后，各线程可以将各自的目标值存储在自己的ThreadLocalMap中，
 * 互不干扰，典型的空间换时间。
 */
public class ThreadLocalTest {

    static ThreadLocal<int[]> globalThreadLocal = new ThreadLocal<>();

    public static void
    main(String[] args) 
    {
        globalThreadLocal.set(new int[] { 1, 2, 3 });
        Thread newThread = new Thread() {
            @Override
            public void run() {
                globalThreadLocal.set(new int[] { 4, 5, 6 });
                System.out.println(Arrays.toString(globalThreadLocal.get()));
            };
        };
        newThread.start();
        System.out.println(Arrays.toString(globalThreadLocal.get()));
    }
}
