package cn.net.bhe.basics.concurrent;

/**
 * ThreadLocal likes a map, and current thread instance is the key.
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("mainThread");
        System.out.println(threadLocal.get());
        Thread newThread = new Thread() {
            @Override
            public void run() {
                threadLocal.set("newThread");
                System.out.println(threadLocal.get());
            };
        };
        newThread.start();
        newThread.join();
    }
}

/*
output:
mainThread
newThread
*/