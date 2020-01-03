package cn.net.bhe.basics.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DML {

    private Connection conn;
    static final Logger LOGGER = LoggerFactory.getLogger(DML.class);

    public static void main(String[] args) throws SQLException {
        DML dml = new DML(Conn.getOracleConn("keep", "123"));
        /*List<List<Object>> rows = dml.query("SELECT * FROM test");
        System.out.println(rows);*/
        dml.execute("DELETE t_test WHERE id = 2");
    }

    public DML(Connection cn) {
        conn = cn;
    }

    public List<List<Object>> query(String sql) {
        ResultSet rs = null;
        List<List<Object>> rows = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFetchSize(100);// not paging
            rs = ps.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            int colsNum = rm.getColumnCount();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                rows.add(row);
                // note that the row and column indexes start at 1
                for (int i = 1; i <= colsNum; i++) {
                    row.add(rs.getObject(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed())
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rows;
    }

    public void execute(String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed())
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
