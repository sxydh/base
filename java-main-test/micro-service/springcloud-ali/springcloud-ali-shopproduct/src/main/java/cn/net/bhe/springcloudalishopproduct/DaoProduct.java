package cn.net.bhe.springcloudalishopproduct;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DaoProduct {
	
	static final Logger LOGGER = LoggerFactory.getLogger(DaoProduct.class);
	
    @PersistenceContext 
    private EntityManager entityManager;
    
    @SuppressWarnings({ "unchecked", "deprecation" })
    public List<Map<String, Object>> test() {
        String sql = " select * from test where 1 = 1 ";
        return (List<Map<String, Object>>) entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
    
    @SuppressWarnings("hiding")
    public <T> T find(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }
    
    public void merge(Object obj) {
    	entityManager.merge(obj);
    }

}
