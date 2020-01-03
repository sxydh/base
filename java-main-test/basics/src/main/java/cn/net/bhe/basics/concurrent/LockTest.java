package cn.net.bhe.basics.concurrent;

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

    public static void main(String[] args) {
        A a = new A();
        A b = new A();

        Thread t1 = new Thread(a);
        t1.setName("t1");
        Thread t2 = new Thread(a);
        t2.setName("t2");
        Thread t3 = new Thread(b);
        t3.setName("t3");
        Thread t4 = new Thread(b);
        t4.setName("t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class A implements Runnable {
    static int i = 0;
    static double j = 0.01;

    /*Object lock*/
    synchronized void synPrintI() {
        i++;
        System.err.println(i + ", " + Thread.currentThread().getName());
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*Object lock*/
    void synCodeBlockPrintI() {
        synchronized (this) {
            i++;
            System.err.println(i + ", " + Thread.currentThread().getName());
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void printI() {
        System.err.println(i + ", " + Thread.currentThread().getName());
        i++;
    }

    /*Object lock*/
    synchronized void synPrintJ() {
        System.err.println(j + ", " + Thread.currentThread().getName());
        j = j + 0.01;
    }

    void printj() {
        System.err.println(j + ", " + Thread.currentThread().getName());
        j = j + 0.01;
    }

    /*Class lock*/
    static synchronized void synClassPrintI() {
        i++;
        System.err.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", " + i + ", " + Thread.currentThread().getName());
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*Class lock*/
    static void synClassCodeBlockPrintI() {
        synchronized (LockTest.class) {
            i++;
            System.err.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", " + i + ", " + Thread.currentThread().getName());
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*Class lock*/
    static synchronized void synClassPrintJ() {
        System.err.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ", " + j + ", " + Thread.currentThread().getName());
        j = j + 0.01;
    }

    @Override
    public void run() {
        /*Object lock test*/
        /*
        if (i == 0) {
            synPrintI();
        }
        synPrintJ();
        */

        /*Class lock test*/
        if (i == 0) {
            synClassPrintI();
        }
        synClassPrintJ();
    }
}
