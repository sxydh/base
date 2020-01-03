package fun.ehe.utils;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

public class DubboClientUtils {

    private static ApplicationConfig application;
    private static RegistryConfig registry;
    static {
        application = new ApplicationConfig();
        application.setName("clientApp");
        registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setUsername("zkAuth");
        registry.setPassword("zkAuth");
    }

    public static <X> X get(Class<X> clazz) {
        ReferenceConfig<X> reference = new ReferenceConfig<X>();

        reference.setApplication(application);
        reference.setRegistry(registry);

        reference.setInterface(clazz);
        reference.setVersion("1.0.0");

        return reference.get();
    }

}
