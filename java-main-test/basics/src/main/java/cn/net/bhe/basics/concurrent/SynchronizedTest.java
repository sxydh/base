package cn.net.bhe.basics.concurrent;

/**
 * 锁持有者是线程，不同线程间争用锁一致时才会产生等待。
 * 只存在对象锁（类锁相当于Class对象锁），没有方法锁。
 * 
 * 可加锁的位置：
 * 1、方法
 * 2、块
 * 
 * <p>
 * Object level lock is mechanism when we want to synchronize a non-static
 * method or non-static code block such that only one thread will be able to
 * execute the code block on given instance of the class. This should always be
 * done to make instance level data thread safe.
 * <p>
 * Class level lock prevents multiple threads to enter in synchronized block in
 * any of all available instances of the class on runtime. This means if in
 * runtime there are 100 instances of DemoClass, then only one thread will be
 * able to execute demoMethod() in any one of instance at a time, and all other
 * instances will be locked for other threads.
 * <p>
 * No method or code block lock.
 * <p>
 */
public class SynchronizedTest {
    
}
