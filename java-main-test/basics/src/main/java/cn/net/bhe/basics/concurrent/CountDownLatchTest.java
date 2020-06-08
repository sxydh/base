package cn.net.bhe.basics.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CountDownLatch的作用是允许1或N个线程等待其他线程完成执行。
 * CountDownLatch实例持有一个计数器，在计数器为0之前，调用该实例await()的所有线程都处于阻塞状态，计数器为0后阻塞的线程继续执行。
 */
public class CountDownLatchTest {
    static Logger log = LoggerFactory.getLogger(CountDownLatchTest.class);
    
    /**
     * 计算线程池任务总耗时一个简单结构
     */
    @Test
    public void timingThreadPool() {
        try {
            // 线程池数量大于等于任务并发数，否则会引发饥饿死锁
            ExecutorService executor = Executors.newFixedThreadPool(10);
            int concurrency = 3;
            
            Runnable work = new Runnable() {
                @Override
                public void run() {
                    try {
                        Random random = new Random();
                        int i = random.nextInt(5);
                        log.info("任务{}执行中，需要{}s", Thread.currentThread().getName(), i);
                        TimeUnit.SECONDS.sleep(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            
            final CountDownLatch ready = new CountDownLatch(concurrency);
            final CountDownLatch start = new CountDownLatch(1);
            final CountDownLatch done = new CountDownLatch(concurrency);
            for (int i = 0; i < concurrency; i++) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        log.info("任务{}准备完毕", Thread.currentThread().getName());
                        ready.countDown(); // 任务准备完毕后，ready计数器减一
                        try {
                            log.info("任务{}进入开始等待中", Thread.currentThread().getName());
                            start.await(); // start计数器为0前，所有任务阻塞
                            work.run();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            done.countDown(); // 任务完成后done计数器减一
                        }
                    }
                });
            }
            ready.await(); // ready计数器为0前，主线程阻塞
            long startNanos = System.nanoTime();
            start.countDown(); // start减一后为0，触发所有被start阻塞的线程
            done.await(); // done计数器为0前，主线程阻塞
            
            log.info("所有任务执行完毕，总耗时{}nanos", System.nanoTime() - startNanos);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
