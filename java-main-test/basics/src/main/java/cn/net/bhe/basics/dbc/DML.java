package cn.net.bhe.basics.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DML {

    private Connection conn;
    static final Logger LOGGER = LoggerFactory.getLogger(DML.class);

    public DML(Connection cn) {
        conn = cn;
    }

    public List<List<Object>> query(String sql) {
        ResultSet rs = null;
        List<List<Object>> rows = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFetchSize(100); // 结果集分批次取，每次取该值大小，如果设置为0，则JDBC取自己的最佳预测值
            rs = ps.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            int colsNum = rm.getColumnCount();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                rows.add(row);
                // 注意行列索引从1开始
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
