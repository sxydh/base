package cn.net.bhe.basics.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        /*for (int i = 0; i < 3; i++) {
            service.execute(new Runnable() {
        
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        
            });
        }*/
        Collection<CallableTask> tasks = new ArrayList<CallableTask>();
        tasks.add(new CallableTask());
        tasks.add(new CallableTask());
        tasks.add(new CallableTask());
        tasks.add(new CallableTask());
        tasks.add(new CallableTask());
        try {
            service.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

class CallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread() + ": " + this);
        return null;
    }

}
