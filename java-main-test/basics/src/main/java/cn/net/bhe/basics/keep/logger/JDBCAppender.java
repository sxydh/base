package cn.net.bhe.basics.keep.logger;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.spi.LoggingEvent;

/**
 * 建议覆盖实现org.apache.log4j.jdbc.JDBCAppender
 * 原因详见http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/jdbc/JDBCAppender.html
 */
public class JDBCAppender extends org.apache.log4j.jdbc.JDBCAppender {

    /*
     * 此处用druid连接池会出现ConcurrentModificationException异常，未解决
     */
    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void closeConnection(Connection connection) {
        super.closeConnection(connection);
    }

    @Override
    public String getLogStatement(LoggingEvent event) {
        return super.getLogStatement(event);
    }

}
