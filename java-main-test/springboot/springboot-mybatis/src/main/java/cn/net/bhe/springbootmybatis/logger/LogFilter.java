package cn.net.bhe.springbootmybatis.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 需要在logback-spring.xml配置此过滤器
 */
public class LogFilter extends Filter<ILoggingEvent> {
    static Logger log =LoggerFactory.getLogger(LogFilter.class);
    
    @Override
    public FilterReply decide(ILoggingEvent event) {
//        System.out.println(event.getFormattedMessage());
        return FilterReply.ACCEPT;
    }
    
}
