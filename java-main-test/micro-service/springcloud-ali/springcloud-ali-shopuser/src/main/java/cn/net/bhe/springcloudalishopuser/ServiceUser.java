package cn.net.bhe.springcloudalishopuser;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {
	
	static final Logger LOGGER = LoggerFactory.getLogger(ServiceUser.class);
	
	@Autowired
	DaoUser daoUser;
	
	public List<Map<String, Object>> test() {
		return daoUser.test();
	}

}
