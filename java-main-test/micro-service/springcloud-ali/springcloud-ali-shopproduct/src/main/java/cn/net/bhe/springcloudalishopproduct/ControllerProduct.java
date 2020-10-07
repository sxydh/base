package cn.net.bhe.springcloudalishopproduct;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerProduct {

    static final Logger Logger = LoggerFactory.getLogger(ControllerProduct.class);

    @Autowired
    ServiceProduct serviceProduct;

    /**
     * 本地测试接口
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/test", "/" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Object test(HttpServletRequest httpServletRequest) {
        return serviceProduct.test();
    }

    /**
     * 获取产品信息
     * @param httpServletRequest
     * @param id
     * @return
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/product/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Object productGetById(HttpServletRequest httpServletRequest, @PathVariable(required = true) String id) {
        return serviceProduct.productGetById(id);
    }

}
