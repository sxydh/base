package fun.ehe.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fun.ehe.dao.TestDao;

@Service
public class TestService {

    static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestDao testDao;

    @Transactional(readOnly = true)
    public Map<String, Object> get(Map<String, Object> rq_m) {
        return testDao.get(rq_m);
    }

}
