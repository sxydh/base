package cn.net.bhe.cmpp.huawei;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.cmpp.Utils;

public class App {

    static Logger log = LoggerFactory.getLogger(App.class);
    static Object lock = new Object();

    private static Socket socket = null;
    private static OutputStream out;
    private static InputStream in;

    ThreadPoolExecutor executor;

    public static void main(String[] args) throws Exception {
        new App();
    }

    public App() {
        init();
    }
    
    public static void resume() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static OutputStream getOut() {
        return out;
    }

    public static InputStream getIn() {
        return in;
    }

    public void init() {
        synchronized (lock) {
            boolean initOver = false;
            int nth = 1;
            while (true) {
                if (initOver) {
                    Utils.wait(lock);
                    log.info("10s后将重启");
                    Utils.sleep(TimeUnit.SECONDS, 10);
                    initOver = false;
                    nth = 1;
                }
                try {
                    log.info("尝试建立Socket，第{}次", nth);
                    socket = new Socket(Config.IP, Config.PORT);
                    out = socket.getOutputStream();
                    in = socket.getInputStream();
                    log.info("建立Socket成功");
                    initOver = true;
                    if (executor == null) {
                        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
                        executor.submit(new ConnectTask());
                        executor.submit(new ReadTask());
                        executor.submit(new ActiveTestTask());
                        executor.submit(new TerminateTask());
                    } else {
                        new Thread(() -> {
                            ConnectTask.resume();
                            ReadTask.resume();
                        }).start();
                    }
                } catch (Exception e) {
                    log.error("尝试建立Socket失败，原因：{}，第{}次", e.getLocalizedMessage(), nth);
                    nth++;
                    if (nth > 3) {
                        log.info("无法获得Socket连接，退出");
                        System.exit(0);
                    }
                    Utils.sleep(TimeUnit.SECONDS, 3);
                }
            }
        }
    }

}
