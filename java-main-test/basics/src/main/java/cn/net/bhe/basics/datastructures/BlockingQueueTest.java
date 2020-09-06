package cn.net.bhe.basics.datastructures;

import static cn.net.bhe.utils.main.PrintUtils.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Test;

/**
 * BlockingQueue获取、存入都是线程安全的
 */
public class BlockingQueueTest {
    
///////////////////////////////////获取
    /*
     * 非阻塞式获取
     * 没有值则返回null
     */
    @Test
    public void poll() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();
        pln(blockingQueue.poll());
    }
    
    /*
     * 阻塞式获取
     * 没有值则会阻塞，阻塞期间可能会抛出InterruptedException
     */
    @Test
    public void take() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();
        try {
            pln(blockingQueue.take());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
///////////////////////////////////存入
    /*
     * 非阻塞式存入
     * 当队列已满时则不放入且返回false
     */
    @Test
    public void offer() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(2);
        pln(blockingQueue.offer(1));
        pln(blockingQueue.offer(2));
        pln(blockingQueue.offer(3)); // 返回false
    }
    
    /*
     * 非阻塞式存入
     * 当队列已满时则不放入且抛出异常
     */
    @Test
    public void add() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(2);
        try {
            pln(blockingQueue.add(1));
            pln(blockingQueue.add(2));
            pln(blockingQueue.add(3)); // 抛出异常java.lang.IllegalStateException: Queue full
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 阻塞式存入
     * 当队列已满时阻塞，阻塞期间可能会抛出InterruptedException
     */
    @Test
    public void put() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(2);
        try {
            blockingQueue.put(1);
            blockingQueue.put(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
