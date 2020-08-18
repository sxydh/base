package cn.net.bhe.springbootmybatis.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class BeanConfig {
    
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor taskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(40);
        executor.setQueueCapacity(300);
        executor.setKeepAliveSeconds(50);
        executor.setThreadNamePrefix("SpringBoot-Executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
