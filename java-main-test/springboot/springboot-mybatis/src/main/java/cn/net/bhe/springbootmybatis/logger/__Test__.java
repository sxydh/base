package cn.net.bhe.springbootmybatis.logger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class __Test__ {
    
    Logger log = LoggerFactory.getLogger(__Test__.class);
    
    /*
     * 手动异常测试
     */
    @PostConstruct
    public void init() {
        try {
            a();
        } catch (Exception e) {
            log.error("测试", e);
        }
    }
    
    public void a() {
        try {
            b();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void b() {
        try {
            c();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void c() {
        throw new RuntimeException("异常源");
    }

}
