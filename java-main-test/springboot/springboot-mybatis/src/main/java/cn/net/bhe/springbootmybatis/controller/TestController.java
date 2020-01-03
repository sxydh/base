package cn.net.bhe.springbootmybatis.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.net.bhe.springbootmybatis.bean.Rt;
import cn.net.bhe.springbootmybatis.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "TestController", description = "")
@RestController
public class TestController extends BaseController {

    static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    
    @ApiIgnore
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String home(HttpServletRequest request) {
        return "Hello World";
    }

    @ApiOperation(value = "Get specific data")
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String get(HttpServletRequest request, @RequestBody Map<String, Object> rq_m) {
        Rt rt = new Rt();
        rt.setData(testService.get(rq_m));
        rt.setSc(Rt.CODE_SUC);
        return toStr(request, rt);
    }

    @ApiOperation(value = "List page data")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String list(HttpServletRequest request, @RequestBody Map<String, Object> rq_m) {
        Rt rt = new Rt();
        rt.setData(testService.list(rq_m));
        rt.setSc(Rt.CODE_SUC);
        return toStr(request, rt);
    }

}
