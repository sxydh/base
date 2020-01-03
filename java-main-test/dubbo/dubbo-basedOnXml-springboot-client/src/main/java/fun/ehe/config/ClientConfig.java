package fun.ehe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath:dubbo-c.xml" })
public class ClientConfig {

}
