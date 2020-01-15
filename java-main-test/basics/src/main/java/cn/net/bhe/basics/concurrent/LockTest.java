package cn.net.bhe.basics.concurrent;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

/**
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
 */
public class LockTest {

    public static void
    lockClass()  // 类锁，只有一个，有锁竞争时都需要等待，即使是不同的 synchronized method
    // main(String[] args)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.random();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.maxInt();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.uuid(true); // 块级锁，不需要类锁，不等待
            }
        }).start();
    }
    
    public static void 
    lockStaticBlock() // 类块级锁，目标一致时才会产生竞争，避免不必要的等待
    // main(String[] args)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.uuid(true);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.uuid(true); // 等待
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.uuid(false); // 不需要锁不等待
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.maxLong(true); // 目标不一致不等待
            }
        }).start();
    }
    
    public static void
    lockObject()  // 对象锁，不同对象不同的锁，其它和类锁类似
    // main(String[] args)
    {
        Task task = new Task();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.now();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.maxDouble();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.zoneId(true);
            }
        }).start();
    }
    
    public static void
    // lockObjectBlock()  // 对象块级锁，和类块级锁类似
    main(String[] args)
    {
        Task task = new Task();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.zoneId(true);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.zoneId(true);
            }
        }).start();
    }
    
}

@SuppressWarnings({ "rawtypes", "unchecked" })
class Task {
    static Integer blockTime = 3000;
    static Object var = new Object();
    
    static synchronized void random() { 
        System.out.println(
                "\r\n"
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + Math.random());
        sleep(blockTime);
    }
    
    static synchronized void maxInt() {
        System.out.println(
                "\r\n"
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + Integer.MAX_VALUE);
        sleep(blockTime);
    }
    
    static void maxLong(boolean needLock) {
        String mname = Thread.currentThread().getStackTrace()[1].getMethodName();
        Function function = new Function() {
            @Override
            public Object apply(Object arg0) {
                System.out.println(
                        "\r\n" 
                        + Thread.currentThread() + ":\r\n"
                        + mname + " -> " + Long.MAX_VALUE);
                return null;
            }
        };
        if (needLock) {
            synchronized (var) {
                function.apply(null);
                sleep(blockTime);
            } 
        } else {
            function.apply(null);
            sleep(blockTime);
        }
    }
    
    static void uuid(boolean needLock) {
        String mname = Thread.currentThread().getStackTrace()[1].getMethodName();
        Function function = new Function() {
            @Override
            public Object apply(Object arg0) {
                System.out.println(
                        "\r\n" 
                        + Thread.currentThread() + ":\r\n"
                        + mname + " -> " + UUID.randomUUID());
                return null;
            }
        };
        if (needLock) {
            synchronized (blockTime) {
                function.apply(null);
                sleep(blockTime);
            } 
        } else {
            function.apply(null);
            sleep(blockTime);
        }
    }
    
    synchronized void now() {
        System.out.println(
                "\r\n" 
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + new Date());
        sleep(blockTime);
    }
    
    synchronized void maxDouble() {
        System.out.println(
                "\r\n" 
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + Double.MAX_VALUE);
        sleep(blockTime);
    }
    
    void zoneId(boolean needLock) {
        String mname = Thread.currentThread().getStackTrace()[1].getMethodName();
        Function function = new Function() {
            @Override
            public Object apply(Object arg0) {
                System.out.println(
                        "\r\n" 
                        + Thread.currentThread() + ":\r\n"
                        + mname + " -> " + ZoneId.systemDefault());
                return null;
            }
        };
        if (needLock) {
            synchronized (blockTime) {
                function.apply(null);
                sleep(blockTime);
            } 
        } else {
            function.apply(null);
            sleep(blockTime);
        }
    }
    
    static void sleep(long blockTime) {
        try {
            Thread.sleep(blockTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
