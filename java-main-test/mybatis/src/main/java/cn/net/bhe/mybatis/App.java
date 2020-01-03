package cn.net.bhe.mybatis;

import org.apache.ibatis.session.SqlSession;

public class App {

    public static void main(String[] args) {
        list();
    }

    public static void test() {
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

    public static void list() {
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
