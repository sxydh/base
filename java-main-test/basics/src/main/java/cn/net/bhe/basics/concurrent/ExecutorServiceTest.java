package cn.net.bhe.basics.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
public class ExecutorServiceTest {

    static Logger log = LoggerFactory.getLogger(ExecutorServiceTest.class);
    
    /**
     * 立即停止线程池的所有任务，但不保证都能终止成功，建议用isTerminated探测状态
     */
    @Test
    public void shutdownNow() {

    }
    
    /**
     * 提交任务到线程池并发执行，并返回任务对应的Future，Future持有对应任务的执行状态
     */
    @Test
    public void submit() {
        try {
            long start = System.currentTimeMillis();
            List<Future<Object>> futures = new ArrayList<Future<Object>>();
            for (Callable<Object> task : tasks) {
                futures.add(service.submit(task));
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
        service.shutdown();
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
        try {
            long start = System.currentTimeMillis();
            List<Future<Object>> futures = service.invokeAll(tasks, 4, TimeUnit.SECONDS); // min(所有任务完成时间，限时)到达之前处于阻塞状态，限时之后没有完成的任务将被取消
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
        service.shutdown();
    }
    
    /**
     * 参考invokeAll
     */
    @Test
    public void invokeAny() {

    }
    
    static ExecutorService service;
    static Collection<Callable<Object>> tasks;
    static {
        service = Executors.newFixedThreadPool(3);
        tasks = new ArrayList<>();
        tasks.add(new Callable<Object>() {
            @Override
            public Map<String, Object> call() throws Exception {
                Random random = new Random();
                int ret = random.nextInt();
                TimeUnit.SECONDS.sleep(40);
                return Map.of(Thread.currentThread().getName(), ret);
            }
        });
        tasks.add(new Callable<Object>() {
            @Override
            public Map<String, String> call() throws Exception {
                String ret = UUID.randomUUID().toString();
                TimeUnit.SECONDS.sleep(30);
//                if (ret != null) throw new RuntimeException("业务异常");
                return Map.of(Thread.currentThread().getName(), ret);
            }
        });
    }
}
