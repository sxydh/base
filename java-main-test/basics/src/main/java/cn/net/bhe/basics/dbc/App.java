package cn.net.bhe.basics.dbc;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleTypes;

public class App {
    static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    @Test
    public void query() {
        DML dml = new DML(Conn.getOracleConn("keep", "123"));
        List<List<Object>> rows = dml.query("SELECT * FROM test");
        System.out.println(rows);
    }
    
    @Test
    public void execute() {
        DML dml = new DML(Conn.getOracleConn("keep", "123"));
        dml.execute("DELETE test WHERE id = 2");
    }
    
    @Test
    public void plsql() throws Exception {
        PLSQL plsql = new PLSQL(Conn.getOracleConn("test", "123456"));
        /* 需要预先创建一个存储过程
        CREATE OR REPLACE PROCEDURE obj_pr(val  out number,
                                           obj  IN obj_type,
                                           objs in objs_type) AS
          temp NUMBER;
        BEGIN
          temp := 0;
          FOR i in 1 .. objs.count LOOP
            temp := temp + obj.numb * objs(i).numb;
          END LOOP;
          val := temp;
        END obj_pr;
         */
        CallableStatement call = plsql.prepareCall("{CALL OBJ_PR(?,?,?)}");
        call.registerOutParameter(1, OracleTypes.NUMBER);
        call.setObject(2, plsql.getSTRUCT("OBJ_TYPE", new Bean(4, 5))); // 注意大小写
        List<Bean> beans = new ArrayList<>();
        beans.add(new Bean(1, 10));
        beans.add(new Bean(2, 90));
        beans.add(new Bean(3, 20));
        call.setArray(3, plsql.getARRAY("OBJ_TYPE", "OBJS_TYPE", beans)); // 注意大小写
        call.execute();
        LOGGER.info(call.getString(1));
    }
}
