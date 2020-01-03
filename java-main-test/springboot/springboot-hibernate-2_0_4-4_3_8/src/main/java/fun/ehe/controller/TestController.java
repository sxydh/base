package fun.ehe.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fun.ehe.bean.Rt;
import fun.ehe.service.TestService;

@RestController
public class TestController {

    static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/get", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object get(@RequestBody Map<String, Object> rq_m) {
        Rt rt = new Rt();
        rt.setData(testService.get(rq_m));
        rt.setSc(Rt.CODE_SUC);
        return rt;
    }

}
