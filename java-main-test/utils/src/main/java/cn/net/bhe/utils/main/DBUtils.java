package cn.net.bhe.utils.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {

    @Test
    public void getComboPooledDataSource() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = getComboPooledDataSource("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:orcl", "bhe", "123");
        System.out.println(comboPooledDataSource.getConnection().createStatement().executeQuery(" select t.* from test t "));
    }

    public static ComboPooledDataSource getComboPooledDataSource(String driver, String url, String user, String password) {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass(driver);
            cpds.setJdbcUrl(url);
            cpds.setUser(user);
            cpds.setPassword(password);
            cpds.setMaxStatements(180);
            return cpds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void getDruidDataSource() {
        DruidDataSource dataSource = getDruidDataSource("jdbc:oracle:thin:@localhost:1521:orcl", "bhe", "123");
        try {
            Connection connection = dataSource.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(" select t.* from test t ");
            System.err.println(resultSet);
            dataSource.getConnection();
            dataSource.getConnection();
            dataSource.getConnection();
            dataSource.getConnection();
            System.err.println(dataSource.getActiveCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDruidDataSource(String url, String user, String password) {
        DruidDataSource dataSource = new com.alibaba.druid.pool.DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(1);
        dataSource.setMaxWait(60000);
        dataSource.setMinIdle(1);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(20);
        dataSource.setAsyncInit(true);
        return dataSource;
    }

}
