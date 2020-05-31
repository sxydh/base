package cn.net.bhe.cmpp.huawei;

import java.net.SocketException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.cmpp.Utils;
import cn.net.bhe.utils.main.IOUtils;

public class ReadTask implements Runnable {

    Logger log = LoggerFactory.getLogger(ReadTask.class);
    static Object lock = new Object();

    @Override
    public void run() {
        try {
            read();
        } catch (Throwable e) {
            log.error("", e);
        }
    }
    
    public static void resume() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void read() {
        synchronized (lock) {
            boolean wait = true;
            while (true) {
                if (wait || App.getSocket() == null || App.getSocket().isClosed() || App.getIn() == null) {
                    log.info("等待读取数据流中");
                    Utils.wait(lock);
                    wait = false;
                }
                try {
                    byte[] msg = new byte[1024];
                    App.getIn().read(msg);
                    int[] msgHead = getMsgHead(msg);
                    if (msgHead[0] == 0) {
                        log.info("数据流到达末尾");
                        wait = true;
                        continue;
                    }
                    byte[] msgBody = getMsgBody(msg);
                    log.info("解析包，消息头：{}，消息体：{}", Arrays.toString(msgHead), Arrays.toString(msgBody));
                    parseMsg(msgHead, msgBody);
                } catch (ArrayIndexOutOfBoundsException e) {
                    log.error("", e);
                } catch (SocketException e) {
                    log.error("", e);
                    wait = true;
                } catch (Exception e) {
                    log.error("读取数据流失败", e);
                }
            }
        }
    }

    public void parseMsg(int[] msgHead, byte[] msgBody) {
        int commandId = msgHead[1];
        switch (commandId) {
        case Config.CommandId.CMPP_CONNECT_RESP:
            int status = msgBody[0];
            log.info("获得一个登录响应，状态：{}", status);
            if (status <= Config.Login.SUC) {
                log.info("登录成功");
                new Thread(() -> {
                    log.info("通知链路线程处理");
                    ActiveTestTask.resume();
                }).start();
            } else {
                log.info("登录失败");
                new Thread(() -> {
                    log.info("通知终止线程处理");
                    TerminateTask.resume();
                }).start();
            }
            break;
        case Config.CommandId.CMPP_ACTIVE_TEST_RESP:
            log.info("收到一个链路检测响应");
            break;
        case Config.CommandId.CMPP_DELIVER:
            log.info("收到一条消MTR/MO消息");
            int registeredDelivery = IOUtils.bytes2int(msgBody[75]);
            if (registeredDelivery == Config.RegisteredDelivery.IS_REPORT) {
                log.info("收到一个状态MT报告");
            } else {
                log.info("收到一个MO");
            }
            break;
        case Config.CommandId.CMPP_TERMINATE:
            log.info("收到一个终止连接命令");
            new Thread(() -> {
                log.info("通知终止线程处理");
                TerminateTask.resume();
            }).start();
            break;
        default:
            break;
        }
    }

    public int[] getMsgHead(byte[] msg) {
        int[] head = new int[3];
        head[0] = IOUtils.bytes2int(msg[0], msg[1], msg[2], msg[3]);
        head[1] = IOUtils.bytes2int(msg[4], msg[5], msg[6], msg[7]);
        head[2] = IOUtils.bytes2int(msg[8], msg[9], msg[10], msg[11]);
        return head;
    }

    public byte[] getMsgBody(byte[] msg) {
        int from = 12;
        int to = IOUtils.bytes2int(msg[0], msg[1], msg[2], msg[3]);
        if (from < to) {
            return Arrays.copyOfRange(msg, from, to);
        }
        return new byte[0];
    }

}
