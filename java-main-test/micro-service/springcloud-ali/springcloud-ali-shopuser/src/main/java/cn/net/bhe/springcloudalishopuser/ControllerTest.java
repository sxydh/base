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
public class ControllerTest {

	static final Logger Logger = LoggerFactory.getLogger(ControllerTest.class);

	@Autowired
	ServiceTest serviceTest;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/list", "/" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object list(HttpServletRequest httpServletRequest) {
		return serviceTest.list();
	}
}
