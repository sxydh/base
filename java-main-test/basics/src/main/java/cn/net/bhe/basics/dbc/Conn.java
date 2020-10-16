package cn.net.bhe.basics.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
DB2:
driverClassName: com.ibm.db2.jcc.DB2Driver
url: jdbc:db2://localhost:50000/test

Oracle:
driverClassName: oracle.jdbc.driver.OracleDriver
url: jdbc:oracle:thin:@localhost:1521:orcl
dialect: org.hibernate.dialect.OracleDialect

MySql:
driverClassName: com.mysql.jdbc.Driver
url: jdbc:mysql://localhost:3306/test
dialect: org.hibernate.dialect.MySQLDialect

H2:
driverClassName: org.h2.Driver
url: jdbc:h2:tcp://localhost/~/test

SQLite
driverClassName: org.sqlite.JDBC
url: jdbc:sqlite:<your database path>\\<database name>.db
*/
public class Conn {
    
    public static Connection getConnection(String driver, String url, String username, String password) {
        Connection conn = null;
        try {
            Class.forName(driver);
            if (username != null && username.length() > 0) {
                conn = DriverManager.getConnection(url, username, password);
            } else {
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getMySqlConn(String username, String password) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        Connection conn = getConnection(driver, url, username, password);
        return conn;
    }

    public static Connection getOracleConn(String username, String password) {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        Connection conn = getConnection(driver, url, username, password);
        return conn;
    }

    public static Connection getSQLiteConn() {
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:<your database path>\\<database name>.db";
        Connection conn = getConnection(driver, url, null, null);
        return conn;
    }

}
