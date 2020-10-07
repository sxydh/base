package cn.net.bhe.springcloudalishopuser;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // Nacos的@RefreshScope实现配置的动态刷新
public class ControllerUser {

	static final Logger Logger = LoggerFactory.getLogger(ControllerUser.class);

	@Autowired
	ServiceUser serviceUser;

	/**
	 * 本地测试接口
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/test", "/" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object test(HttpServletRequest httpServletRequest) {
		return serviceUser.test();
	}
	
    @Value("${myconfig.var1:empty}")
    String myConfigVar1;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/myconfig/var1" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Object myconfig(HttpServletRequest httpServletRequest) {
        return myConfigVar1;
    }
	
}
