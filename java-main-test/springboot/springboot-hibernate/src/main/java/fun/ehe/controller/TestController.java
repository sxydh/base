package fun.ehe.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/getPojo")
    public Object getPojo(@RequestBody Map<String, Object> rq_m) {
        Rt rt = new Rt();
        rt.setData(testService.getPojo(rq_m));
        rt.setSc(Rt.CODE_SUC);
        return rt;
    }

    @PostMapping("/getMap")
    public Object getMap(@RequestBody Map<String, Object> rq_m) {
        Rt rt = new Rt();
        rt.setData(testService.getMap(rq_m));
        rt.setSc(Rt.CODE_SUC);
        return rt;
    }
    
    /**
     * 删除测试
     * @param httpServletRequest
     * @param id					主键
     * @return
     */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/delete/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object list(HttpServletRequest httpServletRequest, @PathVariable int id) {
		return testService.delete(id);
	}

}
