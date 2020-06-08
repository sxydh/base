package cn.net.bhe.basics.concurrent;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 当程序运行时，每个java应用程序都能得到一个运行时的实例，应用程序不能创建这个实例，只能从getRuntime()方法获得RunTime实例。
 */
public class RuntimeTest {
    static Logger log = LoggerFactory.getLogger(RuntimeTest.class);

    /**
     * 新增一个钩子任务线程。在守护线程尝试关闭前会调用所有的钩子任务，其它时间这些任务都处于未开始状态。
     */
    @Test
    public void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                log.info("Shutdown Hook is running !");
            }
        });
        log.info("Application Terminating ...");
    }
}
