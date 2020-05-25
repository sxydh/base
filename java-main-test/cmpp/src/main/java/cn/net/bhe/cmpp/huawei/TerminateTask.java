package cn.net.bhe.cmpp.huawei;

import org.slf4j.LoggerFactory;
import cn.net.bhe.cmpp.Utils;
import org.slf4j.Logger;

public class TerminateTask implements Runnable {

    static Logger log = LoggerFactory.getLogger(TerminateTask.class);

    static Object lock = new Object();

    @Override
    public void run() {
        try {
            terminate();
        } catch (Throwable e) {
            log.error("", e);
        }
    }
    
    public static void resume() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void terminate() {
        synchronized (lock) {
            while (true) {
                Utils.wait(lock);
                if (App.getSocket().isClosed())
                    continue;
                try {
                    log.info("尝试关闭连接");
                    App.getSocket().close();
                    log.info("成功关闭连接");
                    new Thread(() -> {
                        log.info("通知主线程重启");
                        App.resume();
                    }).start();
                } catch (Exception e) {
                    log.error("关闭连接失败", e);
                }
            }
        }
    }

}
