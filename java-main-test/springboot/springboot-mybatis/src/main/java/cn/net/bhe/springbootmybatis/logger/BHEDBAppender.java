package cn.net.bhe.springbootmybatis.logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.db.ConnectionSource;
import ch.qos.logback.core.db.DBHelper;

/**
 * ch.qos.logback.classic.db.DBAppender默认插入三张表
 * logging_event
 * logging_event_exception
 * logging_event_property
 * 创建表脚本位置：
 * logback-classic-1.2.3.jar
 * ch.qos.logback.classic.db.script
 * 
 * 可以重写超类部分方法，用自定义的表来存储日志
 */
public class BHEDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    protected ConnectionSource connectionSource;

    @Override
    public void start() {
        if (connectionSource == null) {
            throw new IllegalStateException("DBAppender cannot function without a connection source");
        }
        super.start();
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }
    
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void append(ILoggingEvent eventObject) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        try {
            connection = connectionSource.getConnection();
            connection.setAutoCommit(false);

            String sql = " insert into t_logback (logdate, priority, pos, throwable, caller, message, thread) values (?, ?, ?, ?, ?, ?, ?) ";
            insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, simpleDateFormat.format(new Date(eventObject.getTimeStamp())));
            insertStatement.setString(2, eventObject.getLevel().toString());
            insertStatement.setString(3, eventObject.getLoggerName());
            
            StringBuffer throwableBuf = new StringBuffer();
            IThrowableProxy throwbleProxy = eventObject.getThrowableProxy();
            if (throwbleProxy != null) {
                throwableBuf.append(ThrowableProxyUtil.asString(throwbleProxy));
            }
            insertStatement.setString(4, throwableBuf.toString());
            
            insertStatement.setString(5, formated(eventObject.getCallerData(), 1));
            insertStatement.setString(6, eventObject.getFormattedMessage());
            insertStatement.setString(7, eventObject.getThreadName());
            
            insertStatement.execute();

            connection.commit();
        } catch (Throwable sqle) {
            addError("problem appending event", sqle);
        } finally {
            DBHelper.closeStatement(insertStatement);
            DBHelper.closeConnection(connection);
        }
    }

    @Override
    public void stop() {
        super.stop();
    }
    
    public static String formated(Object[] objs, int len) {
        if (objs == null || objs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Object obj = objs[i];
            sb.append(obj.toString()).append(CoreConstants.LINE_SEPARATOR);
        }
        return sb.toString();
    }
}
