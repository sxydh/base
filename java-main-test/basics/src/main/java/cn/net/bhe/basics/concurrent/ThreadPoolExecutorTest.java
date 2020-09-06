package cn.net.bhe.basics.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注意，线程池执行任务的过程中不会抛出线程异常
 * 解决办法：
 * 1、手动catch所有的异常，注意Throwable > Exception = Error
 * 2、重新实现线程池
 * 3、利用Future的get接口
 */
public class ThreadPoolExecutorTest {
    static Logger log = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);
    static Collection<Callable<Object>> tasks;
    static {
        tasks = new ArrayList<>();
        IntStream.range(0, 10).forEach((int i) -> {
            tasks.add(new Callable<Object>() {
                @Override
                public Map<String, Object> call() throws Exception {
                    TimeUnit.SECONDS.sleep(2);
                    // if ("".length() == 0) throw new RuntimeException("业务异常");
                    return Map.of(Thread.currentThread().getName(), new Random().nextInt());
                }
            });
        });
    }
    
    /**
     * 立即停止线程池，不再接收新的任务，也不会执行任务队列中的任务，中断所有执行中的任务。
     * 但不保证都能终止成功，建议用isTerminated探测状态
     */
    @Test
    public void shutdownNow() {
        new ThreadPoolExecutor(
                10, 
                10, 
                10, 
                TimeUnit.MICROSECONDS, 
                new LinkedBlockingQueue<Runnable>(10), 
                Executors.defaultThreadFactory(), 
                new AbortPolicy()).shutdownNow();
    }
    
    /**
     * 提交任务到线程池并发执行，并返回任务对应的Future，Future持有对应任务的执行状态
     */
    @Test
    public void submit() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 
                10, 
                10, 
                TimeUnit.MICROSECONDS, 
                new LinkedBlockingQueue<Runnable>(10), 
                Executors.defaultThreadFactory(), 
                new AbortPolicy());
        try {
            long start = System.currentTimeMillis();
            List<Future<Object>> futures = new ArrayList<Future<Object>>();
            for (Callable<Object> task : tasks) {
                futures.add(threadPoolExecutor.submit(task));
            }
            log.info("提交任务完毕");
            int suc = 0;
            for (Future<Object> future : futures) {
                try {
                    log.info("线程返回：{}", future.get()); // Future的get接口返回线程执行结果，包括线程异常，任务完成前，此接口阻塞
                    suc++;
                } catch (Exception e) {
                    log.error("线程异常", e);
                }
            }
            log.info("任务数量：{}，成功执行数量：{}，耗时：{}ms", futures.size(), suc, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("", e);
        }
        threadPoolExecutor.shutdown();
    }
    
    /**
     * 参考submit，但是不会返回任务对应的Future
     */
    @Test
    public void execute() {

    }

    /**
     * 执行线程池任务，并返回所有任务对应的Future列表，Future持有对应任务的执行状态
     * 注意，min(所有任务完成时间，限时)到达之前，调用invokeAll的线程将会处于阻塞状态
     */
    @Test
    public void invokeAll() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 
                10, 
                10, 
                TimeUnit.MICROSECONDS, 
                new LinkedBlockingQueue<Runnable>(10), 
                Executors.defaultThreadFactory(), 
                new AbortPolicy());
        try {
            long start = System.currentTimeMillis();
            List<Future<Object>> futures = threadPoolExecutor.invokeAll(tasks, 4, TimeUnit.SECONDS); // min(所有任务完成时间，限时)到达之前处于阻塞状态，限时之后没有完成的任务将被取消
            int suc = 0;
            for (Future<Object> future : futures) {
                try {
                    log.info("线程返回：{}", future.get()); // Future的get接口返回线程执行结果，包括线程异常
                    suc++;
                } catch (Exception e) {
                    log.error("线程异常", e);
                }
            }
            log.info("任务数量：{}，成功执行数量：{}，耗时：{}ms", futures.size(), suc, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("", e);
        }
        threadPoolExecutor.shutdown();
    }
    
    /**
     * 参考invokeAll
     */
    @Test
    public void invokeAny() {

    }
    
}
