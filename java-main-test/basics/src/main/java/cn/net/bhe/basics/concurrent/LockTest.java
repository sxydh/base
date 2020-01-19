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
 * <p>
 * 锁一致时才会产生等待
 */
@SuppressWarnings("static-access")
public class LockTest {

    public static void
    lockClass()  // 类锁，只有一个，static method 有 synchronized 的都需要类锁
    // main(String[] args)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.random(); // 需要类锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.maxInt(); // 需要类锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Task().random(); // 需要类锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.uuid(true); // 需要 target1 锁
            }
        }).start();
    }
    
    public static void
    lockObject()  // 对象锁，不同对象不同的锁
    // main(String[] args)
    {
        Task task = new Task();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.now(); // 需要对象锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.maxDouble(); // 需要对象锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Task().now(); // 需要对象锁
            }
        }).start();
    }
    
    public static void 
    // lockTarget() // 块级锁，作用域内才会持有，作用域外会释放，且目标一致时才会产生等待
    main(String[] args)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task.uuid(true); // 需要 target1 锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Task().zoneId(true); // 需要 target1 锁
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Task().maxFloat(true); // 需要 target3 锁
            }
        }).start();
    }
    
}

@SuppressWarnings({ "rawtypes", "unchecked" })
class Task {
    static Integer blockTime = 3000;
    static Object target1 = new Object();
    static Object target2 = new Object();
    Object target3 = new Object();
    
    /**
     * Task.class lock
     */
    static synchronized void random() { 
        System.out.println(
                "\r\n"
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + Math.random());
        sleep(blockTime);
    }
    
    /**
     * Task.class lock
     */
    static synchronized void maxInt() {
        System.out.println(
                "\r\n"
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + Integer.MAX_VALUE);
        sleep(blockTime);
    }
    
    /**
     * target1 lock
     * @param needLock
     */
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
            synchronized (target1) {
                function.apply(null);
                sleep(blockTime);
            } 
        } else {
            function.apply(null);
            sleep(blockTime);
        }
    }
    
    /**
     * target2 lock
     * @param needLock
     */
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
            synchronized (target2) {
                function.apply(null);
                sleep(blockTime);
            } 
        } else {
            function.apply(null);
            sleep(blockTime);
        }
    }
    
    /**
     * Task object lock
     */
    synchronized void now() {
        System.out.println(
                "\r\n" 
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + new Date());
        sleep(blockTime);
    }
    
    /**
     * Task object lock
     */
    synchronized void maxDouble() {
        System.out.println(
                "\r\n" 
                + Thread.currentThread() + ":\r\n"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + Double.MAX_VALUE);
        sleep(blockTime);
    }
    
    /**
     * target1 lock
     * @param needLock
     */
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
            synchronized (target1) {
                function.apply(null);
                sleep(blockTime);
            } 
        } else {
            function.apply(null);
            sleep(blockTime);
        }
    }
    
    /**
     * target3 lock
     * @param needLock
     */
    void maxFloat(boolean needLock) {
        String mname = Thread.currentThread().getStackTrace()[1].getMethodName();
        Function function = new Function() {
            @Override
            public Object apply(Object arg0) {
                System.out.println(
                        "\r\n" 
                        + Thread.currentThread() + ":\r\n"
                        + mname + " -> " + Float.MAX_VALUE);
                return null;
            }
        };
        if (needLock) {
            synchronized (target3) {
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
