package cn.net.bhe.springbootmybatis.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.net.bhe.springbootmybatis.mapper.TestMapper;

@Service
public class TestService {

    static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestMapper testMapper;

    @Transactional(readOnly = true)
    public Map<String, Object> get(Map<String, Object> rq_m) {
        return testMapper.get(rq_m);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> list(Map<String, Object> rq_m) {
        return testMapper.list(rq_m);
    }

}
