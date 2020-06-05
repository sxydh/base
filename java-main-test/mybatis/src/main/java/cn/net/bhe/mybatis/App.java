package cn.net.bhe.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class App {
    
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
