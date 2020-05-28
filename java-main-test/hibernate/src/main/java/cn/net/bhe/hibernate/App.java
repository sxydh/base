package cn.net.bhe.hibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.utils.main.SerializeUtils;

public class App {

    static Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    @Test
    public void keep() {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        
        transaction.commit();
        HibernateUtils.closeSession();
    }
    
    @Test
    public void passList() {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<?> list = session.createSQLQuery(" SELECT * FROM t_member WHERE id IN (:ids) ") // 新版本不加括号
        .setParameterList("ids", Arrays.asList(new Integer[] { 1, 2, 3 })) // 新版本直接用setParameter
        .list();
        transaction.commit();
        HibernateUtils.closeSession();
        LOGGER.info(list.toString());
    }
    
    @Test
    public void passNull() {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createSQLQuery(" INSERT INTO test (id) VALUES (:id) ")
        .setParameter("id", null, StandardBasicTypes.DOUBLE)
        .executeUpdate();
        transaction.commit();
        HibernateUtils.closeSession();
    }

    @Test
    public void intOverflow() {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        while (true) {
            @SuppressWarnings("resource")
            int i = new Scanner(System.in).nextInt();
            if (i == -1) {
                break;
            }

            // entity
            Test test = null;
            try {
                test = (Test) session.createSQLQuery(" SELECT * FROM test WHERE id = :id").addEntity(Test.class)
                        .setInteger("id", i).uniqueResult();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            if (test != null) {
                System.out.println("session.update(test)");
                session.update(test);
            }
            System.out.println(test);
            System.out.println("-----------");

            // sql
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) session.createSQLQuery("SELECT * FROM test WHERE id = :id").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .setInteger("id", i).uniqueResult();
            System.out.println(map);
            System.out.println("-----------");
        }

        transaction.commit();
        HibernateUtils.closeSession();
    }

    @Test
    public void loopQuery() {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        while (true) {
            @SuppressWarnings("resource")
            int i = new Scanner(System.in).nextInt();
            if (i == -1) {
                break;
            }

            // entity
            Test test = (Test) session.createSQLQuery("SELECT * FROM test WHERE id = :id").addEntity(Test.class)
                    .setInteger("id", i).uniqueResult();
            System.out.println(test);
            System.out.println("-----------");

            // sql
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) session.createSQLQuery("SELECT * FROM test WHERE id = :id").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .setInteger("id", i).uniqueResult();
            System.out.println(map);
            System.out.println("-----------");
        }

        transaction.commit();
        HibernateUtils.closeSession();
    }

    @Test
    public void mapList() throws Exception {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> maps = session.createSQLQuery("SELECT * FROM t_group ").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        transaction.commit();
        HibernateUtils.closeSession();
        LOGGER.info(SerializeUtils.toStr(maps));
    }

    @Test
    public void list() {
        Session session = HibernateUtils.currentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<?> list = session.createSQLQuery("SELECT * FROM test").setResultTransformer(Transformers.TO_LIST).list();
        transaction.commit();
        HibernateUtils.closeSession();
        LOGGER.info(list.toString());
    }

}