package fun.ehe.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

public enum DubboServerUtils {

    ;
    static Logger LOGGER = LoggerFactory.getLogger(DubboServerUtils.class);

    private static ApplicationConfig application;
    private static RegistryConfig registry;
    private static ProtocolConfig protocol;
    static {
        application = new ApplicationConfig();
        application.setName("serverApp");

        registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setUsername("zkAuth");
        registry.setPassword("zkAuth");

        protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(200);
    }

    public static <X> void register(X ref) {
        ServiceConfig<X> service = new ServiceConfig<X>();

        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);

        service.setInterface(ref.getClass().getInterfaces()[0]);
        service.setRef(ref);
        service.setVersion("1.0.0");

        service.export();
    }

}
