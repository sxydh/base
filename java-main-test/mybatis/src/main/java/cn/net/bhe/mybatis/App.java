package cn.net.bhe.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    static Logger log = LoggerFactory.getLogger(App.class);

    @Test
    public void test() {
        SqlSession session = Config.getSqlSessionFactory().openSession();
        try {
            TestMapper mapper = session.getMapper(TestMapper.class);
            Map<String, Object> req = new HashMap<String, Object>();
            req.put("value", Math.random());
            req.put("id", 1);
            Object obj = mapper.test(req);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
    }

    /**
     * xml形式
     */
    @Test
    public void xml() {
        SqlSession session = Config.getSqlSessionFactory().openSession();
        try {
            TestMapper mapper = session.getMapper(TestMapper.class);
            System.out.println(mapper.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 注解形式
     */
    @Test
    public void annotation() {
        SqlSession session = Config.getSqlSessionFactory().openSession();
        try {
            TestMapper mapper = session.getMapper(TestMapper.class);
            System.out.println(mapper.list());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
