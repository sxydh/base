package cn.net.bhe.mybatis;

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
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    /**
     * xml形式
     */
    @Test
    public void get() {
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
    public void list() {
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
