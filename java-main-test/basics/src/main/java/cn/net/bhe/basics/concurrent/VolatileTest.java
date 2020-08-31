package cn.net.bhe.basics.concurrent;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 当一个变量定义为volatile之后，它将具备两种特性，第一是保证此变量对所有线程的可见性，这里的“可见性”是指当
 * 一条线程修改了这个变量的值，新值对于其他线程来说是可以立即得知的。而普通变量不能做到这一点，普通变量的值
 * 在线程间传递均需要通过主内存来完成，例如，线程A修改一个普通变量的值，然后向主内存进行回写，另外一条线程B
 * 在线程A回写完成了之后再从主内存进行读取操作，新变量值才会对线程B可见。
 * 关于volatile变量的可见性，经常会被开发人员误解，认为以下描述成立：“volatile变量对所有线程是立即可见的，对
 * volatile变量所有的写操作都能立刻反应到其他线程之中，换句话说，volatile变量在各个线程中是一致的，所以基于
 * volatile变量的运算在并发下是安全的”。这句话的论据部分并没有错，但是其论据并不能得出“基于volatile变量的运算
 * 在并发下是安全的”这个结论。volatile变量在各个线程的工作内存中不存在一致性问题（在各个线程的工作内存中，
 * volatile变量也可以存在不一致的情况，但由于每次使用之前都要先刷新，执行引擎看不到不一致的情况，因此可以认为
 * 不存在一致性问题），但是Java里面的运算并非原子操作，导致volatile变量的运算在并发下一样是不安全的
 * 
 * 使用volatile变量的第二个语义是禁止指令重排序优化，具体见《深入理解Java虚拟机：JVM高级特性与最佳实践(第2版)》
 * P369
 */
public class VolatileTest {
    static Logger log = LoggerFactory.getLogger(VolatileTest.class);

    /**
     * 加数测试，正确结果因为100000，实际结果总是小于正确结果
     */
    volatile int race = 0;
    @Test
    public void addNum() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        /*
                         * 某些情况下volatile并不能带来线程安全
                         * 
                         * 问题就出现在自增运算“race++”之中，当getstatic指令把race的值取到操作栈顶时，
                         * volatile关键字保证了race的值在此时是正确的，但是在执行iconst_1、iadd这些指令的
                         * 时候，其他线程可能已经把race的值加大了，而在操作栈顶的值就变成了过期的数据，
                         * 所以putstatic指令执行后就可能把较小的race值同步回主内存之中。
                         */
                        race++;
                    }
                }
            });
            task.start();
        }
        while (Thread.activeCount() > 2) { // 用了junit所以活动线程至少是2
            Thread.yield();
        }
        log.info(race + "");
    }
    
}