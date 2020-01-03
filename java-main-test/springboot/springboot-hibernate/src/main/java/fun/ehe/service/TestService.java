package fun.ehe.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fun.ehe.bean.Pojo;
import fun.ehe.dao.TestDao;

@Service
public class TestService {

    static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestDao testDao;

    @Transactional(readOnly = true)
    public Pojo getPojo(Map<String, Object> rq_m) {
        return testDao.getPojo(rq_m);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getMap(Map<String, Object> rq_m) {
        return testDao.getMap(rq_m);
    }

}
