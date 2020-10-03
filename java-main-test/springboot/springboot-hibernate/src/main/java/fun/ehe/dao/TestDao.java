package fun.ehe.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import fun.ehe.bean.Pojo;

@Repository
public class TestDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Pojo getPojo(Map<String, Object> rq_m) {
        String sql = " SELECT id, name, ctime, utime FROM t_member WHERE id = :id ";
        return (Pojo) entityManager.createNativeQuery(sql, Pojo.class)
                .setParameter("id", rq_m.get("id")).getSingleResult();
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    public Map<String, Object> getMap(Map<String, Object> rq_m) {
        String sql = " SELECT id \"id\", name \"name\", ctime \"ctime\", utime \"utime\" FROM t_member WHERE id = :id ";
        return (Map<String, Object>) entityManager.createNativeQuery(sql)
                .setParameter("id", rq_m.get("id"))
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }
    
	public int delete(int id) {
		String sql = " DELETE FROM test WHERE id = :id ";
		return entityManager.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

}
