package cn.net.bhe.cmpp.huawei;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.cmpp.Utils;

public class ActiveTestTask implements Runnable {

    Logger log = LoggerFactory.getLogger(ActiveTestTask.class);
    static Object lock = new Object();
    
    @Override
    public void run() {
        try {
            activeTest();
        } catch (Throwable e) {
            log.error("", e);
        }
    }
    
    public static void resume() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void activeTest() {
        synchronized (lock) {
            boolean wait = true;
            while (true) {
                if (wait || App.getSocket() == null || App.getSocket().isClosed() || App.getOut() == null) {
                    log.info("等待发送链路检测包");
                    Utils.wait(lock);
                    wait = false;
                }
                try {
                    log.info("尝试发送链路检测包");
                    byte[] msg = new byte[12];
                    int i = 0;
                    // 链路检测消息 = 消息头，不需要消息体
                    // 消息头
                    System.arraycopy(Utils.int24byteArray(12), 0, msg, i, 4);
                    i += 4;
                    System.arraycopy(Utils.int24byteArray(8), 0, msg, i, 4);
                    i += 4;
                    System.arraycopy(Utils.int24byteArray(Utils.getSequenceId()), 0, msg, i, 4);
                    App.getOut().write(msg);
                    App.getOut().flush();
                    log.info("成功发送链路检测包");
                } catch (SocketException e) {
                    log.error("", e);
                    wait = true;
                } catch (Exception e) {
                    log.error("发送链路检测包异常", e);
                }
                Utils.sleep(TimeUnit.SECONDS, 10);
            }
        }
    }

}
