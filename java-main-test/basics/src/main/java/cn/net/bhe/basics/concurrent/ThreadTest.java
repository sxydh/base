package cn.net.bhe.basics.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {

    static Logger log = LoggerFactory.getLogger(ThreadTest.class);

    /**
     * 对一个阻塞中的线程调用interrupt时会抛出InterruptedException
     * 
     * If this thread is blocked in an invocation of the wait(), wait(long), or
     * wait(long, int) methods of the Object class, or of the join(), join(long),
     * join(long, int), sleep(long), or sleep(long, int), methods of this class,
     * then its interrupt status will be cleared and it will receive an
     * InterruptedException.
     * 
     * If this thread is blocked in an I/O operation upon an InterruptibleChannel
     * then the channel will be closed, the thread's interrupt status will be set,
     * and the thread will receive a java.nio.channels.ClosedByInterruptException.
     * 
     * If this thread is blocked in a java.nio.channels.Selector then the thread's
     * interrupt status will be set and it will return immediately from the
     * selection operation, possibly with a non-zero value, just as if the
     * selector's wakeup method were invoked.
     * 
     * If none of the previous conditions hold then this thread's interrupt status
     * will be set. Interrupting a thread that is not alive need not have any
     * effect.
     */
    @Test
    public void interrupt() throws Exception {
        Thread task = new Thread(() -> {
            synchronized (this) {
                try {
                    log.info("任务线程阻塞中");
                    this.wait();
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            }
        });
        task.start();
        TimeUnit.SECONDS.sleep(2);
        log.info("主线程中断任务线程");
        task.interrupt();
    }

    /**
     * 启动线程，让线程处于就绪状态，且不可重复start
     * 
     * Causes this thread to begin execution; the Java Virtual Machine calls the
     * <code>run</code> method of this thread.
     * <p>
     * The result is that two threads are running concurrently: the current thread
     * (which returns from the call to the <code>start</code> method) and the other
     * thread (which executes its <code>run</code> method).
     * <p>
     * It is never legal to start a thread more than once. In particular, a thread
     * may not be restarted once it has completed execution.
     *
     * @exception IllegalThreadStateException if the thread was already started.
     * @see #run()
     * @see #stop()
     */
    @Test
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("");
            }
        }).start();
        log.info("");
    }

    /**
     * 立即执行线程任务（注意是在调用方所在线程中执行，这里就是主线程），相当于直接调用任务方法，可重复run
     * 
     * If this thread was constructed using a separate <code>Runnable</code> run
     * object, then that <code>Runnable</code> object's <code>run</code> method is
     * called; otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Test
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("");
            }
        }).run();
        log.info("");
    }

    /**
     * 线程休眠
     * 
     * Causes the currently executing thread to sleep (temporarily cease execution)
     * for the specified number of milliseconds, subject to the precision and
     * accuracy of system timers and schedulers. The thread does not lose ownership
     * of any monitors.
     */
    @Test
    public void sleep() throws Exception {
        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    log.info("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
        task.join();
        log.info("");
    }

    /**
     * 调用某个对象obj1的wait()方法时，所在线程thread1必须持有obj1的锁，调用后thread1阻塞，并释放obj1的锁，直到其它线程调用
     * obj1的notify()或notifyAll()后且thread1获得obj1锁时才会恢复执行。
     * 
     * Causes the current thread to wait until another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object. In other words,
     * this method behaves exactly as if it simply performs the call
     * {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread releases
     * ownership of this monitor and waits until another thread notifies threads
     * waiting on this object's monitor to wake up either through a call to the
     * {@code notify} method or the {@code notifyAll} method. The thread then waits
     * until it can re-obtain ownership of the monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are possible,
     * and this method should always be used in a loop:
     * 
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait();
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * 
     * This method should only be called by a thread that is the owner of this
     * object's monitor. See the {@code notify} method for a description of the ways
     * in which a thread can become the owner of a monitor.
     * 
     * @see java.lang.Object#notify()
     * @see java.lang.Object#notifyAll()
     */
    @Test
    public void await() {
        // 利用wait()实现特定线程优先执行
        // 其它经典例子https://www.programcreek.com/2009/02/notify-and-wait-example/
        Function<Object, Object> print = new Function<Object, Object>() {
            @Override
            public Object apply(Object obj) {
                log.info(obj + "");
                return null;
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    print.apply(i);
                }
                synchronized (print) {
                    print.notify(); // notify()后主线程并不会立即执行，除非获得了print锁
                    try {
                        TimeUnit.SECONDS.sleep(2);;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        synchronized (print) {
            try {
                print.wait(); // 释放print锁并阻塞当前线程，直到newThread调用print的notify()且获得print锁
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        print.apply(new Date());
    }

    /**
     * 某个线程thread1调用了某个对象obj1的notify()时会随机唤醒一个调用了obj1.wait()方法的线程thread2，让thread2得以继续执行（前提是获得了obj1的锁）。
     * thread1调用obj1的notify()时必须持有obj1的锁。
     * 
     * Wakes up a single thread that is waiting on this object's monitor. If any
     * threads are waiting on this object, one of them is chosen to be awakened. The
     * choice is arbitrary and occurs at the discretion of the implementation. A
     * thread waits on an object's monitor by calling one of the {@code wait}
     * methods.
     * <p>
     * The awakened thread will not be able to proceed until the current thread
     * relinquishes the lock on this object. The awakened thread will compete in the
     * usual manner with any other threads that might be actively competing to
     * synchronize on this object; for example, the awakened thread enjoys no
     * reliable privilege or disadvantage in being the next thread to lock this
     * object.
     * <p>
     * This method should only be called by a thread that is the owner of this
     * object's monitor. A thread becomes the owner of the object's monitor in one
     * of three ways:
     * <ul>
     * <li>By executing a synchronized instance method of that object.
     * <li>By executing the body of a {@code synchronized} statement that
     * synchronizes on the object.
     * <li>For objects of type {@code Class,} by executing a synchronized static
     * method of that class.
     * </ul>
     * <p>
     * Only one thread at a time can own an object's monitor.
     *
     * @see java.lang.Object#notifyAll()
     * @see java.lang.Object#wait()
     */
    @Test
    public void notif() {

    }

}
