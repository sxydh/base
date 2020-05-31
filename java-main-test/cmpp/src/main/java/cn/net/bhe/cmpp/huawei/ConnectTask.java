package cn.net.bhe.cmpp.huawei;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.cmpp.Utils;
import cn.net.bhe.utils.main.DateUtils;
import cn.net.bhe.utils.main.MD5Utils;

public class ConnectTask implements Runnable {

    Logger log = LoggerFactory.getLogger(ConnectTask.class);
    static Object lock = new Object();

    @Override
    public void run() {
        try {
            connect();
        } catch (Throwable e) {
            log.error("", e);
        }
    }
    
    public static void resume() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void connect() {
        synchronized (lock) {
            int nth = 1;
            boolean sendOver = false;
            while (true) {
                if (sendOver || App.getSocket() == null || App.getSocket().isClosed() || App.getOut() == null) {
                    log.info("等待发送登录包中");
                    Utils.wait(lock);
                    nth = 1;
                    sendOver = false;
                }
                try {
                    log.info("尝试发送登录包，第{}次", nth);
                    // 消息内容 = 消息头 + 消息体，总长39字节
                    byte[] msg = new byte[39];

                    // 消息头 = 消息总长度 + 命令 + 消息流水号
                    int i = 0;
                    System.arraycopy(Utils.int24byteArray(39), 0, msg, i, 4); // 定长4字节，消息总长度(含消息头及消息体)
                    i += 4;
                    System.arraycopy(Utils.int24byteArray(1), 0, msg, i, 4); // 定长4字节，连接命令
                    i += 4;
                    System.arraycopy(Utils.int24byteArray(Utils.getSequenceId()), 0, msg, i, 4); // 定长4字节，消息序列号，顺序累加，步长为1
                    i += 4;

                    // 消息体 = 源地址 + 密匙 + 版本 + 时间戳
                    byte[] sourceAddr = Config.SPID.getBytes();
                    System.arraycopy(sourceAddr, 0, msg, i, sourceAddr.length); // 定长6字节，源地址，此处为SP_Id，即SP的企业代码
                    i += 6;

                    // 生成密匙
                    // 密匙 = MD5(源地址 + 9字节的0 + sharedSecret + 时间戳)，16字节
                    byte[] temp = new byte[100];
                    int j = 0;
                    System.arraycopy(sourceAddr, 0, temp, j, sourceAddr.length); // 源地址
                    j += sourceAddr.length;
                    j += 9; // 9字节的0
                    byte[] sharedSecret = Config.PWD.getBytes();
                    System.arraycopy(sharedSecret, 0, temp, j, sharedSecret.length); // sharedSecret
                    j += sharedSecret.length;
                    String time = DateUtils.format(new Date(), "MMddHHmmss");
                    byte[] timeStamp = time.getBytes();
                    System.arraycopy(timeStamp, 0, temp, j, timeStamp.length); // 时间戳
                    j += timeStamp.length;
                    byte[] authenticatorSource = new byte[j];
                    System.arraycopy(temp, 0, authenticatorSource, 0, j);
                    authenticatorSource = MD5Utils.to16Bytes(authenticatorSource);

                    System.arraycopy(authenticatorSource, 0, msg, i, 16); // 定长16字节，密匙
                    i += 16;

                    msg[i] = (byte) 1; // 定长1字节，版本
                    i++;
                    System.arraycopy(Utils.int24byteArray(Integer.valueOf(time)), 0, msg, i, 4); // 定长4字节，时间戳

                    App.getOut().write(msg);
                    App.getOut().flush();
                    log.info("成功发送登录包");
                    sendOver = true;
                    new Thread(() -> {
                        log.info("通知读取线程处理");
                        ReadTask.resume();
                    }).start();
                } catch (Exception e) {
                    log.error("发送登录包异常，原因：{}，第{}次", e.getLocalizedMessage(), nth);
                    nth++;
                    if (nth > 3) {
                        log.info("无法发送登录包，退出");
                        System.exit(0);
                    }
                    Utils.sleep(TimeUnit.SECONDS, 3);
                }
            }
        }
    }

}
