package cn.net.bhe.basics.io;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.jupiter.api.Test;

/**
 * WatchService用于文件监控
 */
public class WatchServiceTest {

    /**
     * 监控目录的简单实现
     */
    @Test
    public void directoryWatch() throws Exception {
        String watchPath = "C:\\Users\\Administrator\\Desktop";
        @SuppressWarnings("rawtypes")
        Kind[] kinds = new Kind[] {
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.OVERFLOW };
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(watchPath);
        path.register(watchService, kinds);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind: " + event.kind() + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }
}
