package cn.net.bhe.basics.dbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class PLSQL {

    private Connection conn = null;
    static final Logger LOGGER = LoggerFactory.getLogger(PLSQL.class);

    public PLSQL(Connection cn) {
        conn = cn;
    }

    public CallableStatement prepareCall(String plsql) throws SQLException {
        return conn.prepareCall(plsql);
    }

    public STRUCT getSTRUCT(String obj_type_name, Object obj) throws SQLException {
        StructDescriptor structdes = new StructDescriptor(obj_type_name, conn);
        STRUCT struct = new STRUCT(structdes, conn, getValues(obj));
        return struct;
    }

    public ARRAY getARRAY(String obj_type_name, String objs_type_name, List<?> obj_list) throws SQLException {
        STRUCT[] structs = new STRUCT[obj_list.size()];
        for (int i = 0; i < obj_list.size(); i++) {
            StructDescriptor structdes = new StructDescriptor(obj_type_name, conn);
            structs[i] = new STRUCT(structdes, conn, getValues(obj_list.get(i)));
        }
        ArrayDescriptor arraydes = ArrayDescriptor.createDescriptor(objs_type_name, conn);
        return new ARRAY(arraydes, conn, structs);
    }

    private Object[] getValues(Object bean) {
        List<Object> values = new ArrayList<>();
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            try {
                Method m = bean.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                Object value = m.invoke(bean);
                values.add(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return values.toArray();
    }
}

class Bean {

    private Integer id;
    private Integer numb;

    public Bean(int id, int num) {
        this.id = id;
        this.numb = num;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumb() {
        return numb;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }

}
