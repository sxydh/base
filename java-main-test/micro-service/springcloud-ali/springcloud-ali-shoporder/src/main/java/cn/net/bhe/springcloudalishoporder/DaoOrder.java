package cn.net.bhe.springcloudalishoporder;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DaoOrder {
	
	static final Logger LOGGER = LoggerFactory.getLogger(DaoOrder.class);
	
    @PersistenceContext 
    private EntityManager entityManager;
    
    @SuppressWarnings({ "unchecked", "deprecation" })
    public List<Map<String, Object>> test() {
        String sql = " select * from test where 1 = 1 ";
        return (List<Map<String, Object>>) entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
    
    public void merge(Object obj) {
    	entityManager.merge(obj);
    }

}
