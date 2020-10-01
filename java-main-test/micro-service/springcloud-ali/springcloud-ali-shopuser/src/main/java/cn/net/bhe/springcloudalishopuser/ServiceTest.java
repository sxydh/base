package cn.net.bhe.springcloudalishopuser;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {
	
	static final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
	
	@Autowired
	DaoTest daoTest;
	
	public List<Map<String, Object>> list() {
		return daoTest.list();
	}

}
