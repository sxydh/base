package fun.ehe.dao;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> get(Map<String, Object> rq_m) {
        String sql = " SELECT id \"id\", name \"name\", ctime \"ctime\", utime \"utime\" FROM t_member WHERE id = :id ";
        return (Map<String, Object>) getSession().createSQLQuery(sql)
                //
                .setParameter("id", rq_m.get("id"))
                //
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

}
