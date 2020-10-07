package cn.net.bhe.springcloudalishopuser;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
