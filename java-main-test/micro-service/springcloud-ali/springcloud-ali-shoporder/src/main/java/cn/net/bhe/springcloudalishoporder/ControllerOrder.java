package cn.net.bhe.springcloudalishoporder;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.net.bhe.springcloudalicommon.bean.Order;
import cn.net.bhe.springcloudalicommon.bean.Product;
import cn.net.bhe.springcloudalishoporder.__SpringCloudAliShopOrder__.ProductService;

@RestController
public class ControllerOrder {

	static final Logger Logger = LoggerFactory.getLogger(ControllerOrder.class);

	@Autowired
	ServiceOrder serviceOrder;
	@Autowired
	DiscoveryClient discoveryClient;
	@Autowired
	RocketMQTemplate rocketMQTemplate;

	/**
	 * 本地测试接口
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/test", "/" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object test(HttpServletRequest httpServletRequest) {
		return serviceOrder.test();
	}
	
	@Autowired
    ProductService productService;
	
	/**
	 * 下单
	 * @param httpServletRequest
	 * @param pid
	 * @return
	 */
	@RequestMapping(path = { "/order/product/{pid}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object order(HttpServletRequest httpServletRequest, @PathVariable(required = true) String pid) {
	    Object[] retAndServiceInstance = null;
	    
	    // 获取产品信息
	    
//	    // 自定义负载均衡
//		retAndServiceInstance = customLoadBalanced("service-shopproduct", "/product/".concat(String.valueOf(pid)), Product.class);
		
//		// 基于Ribbon的负载均衡
//		retAndServiceInstance = ribbonLoadBalanced("service-shopproduct", "/product/".concat(String.valueOf(pid)), Product.class);
	    
	    // 基于Feign的服务调用
	    // Feign是Spring Cloud提供的一个声明式的伪Http客户端，它使得调用远程服务就像调用本地服务一样简单，只需要创建一个接口并添加一个注解即可。
	    // Nacos很好的兼容了Feign，Feign默认集成了Ribbon，所以在Nacos下使用Feign默认就实现了负载均衡的效果。
	    retAndServiceInstance = new Object[2];
	    retAndServiceInstance[0] = productService.productGetById(pid);
	    retAndServiceInstance[1] = "feign";
	 
		Product product = (Product) retAndServiceInstance[0];
		
		// 下单
		Order order = serviceOrder.order(product);
        
//        // 向订阅者发送消息
//        rocketMQTemplate.convertAndSend("topic-order", order);
        
        return new Object[] { order, retAndServiceInstance[1] };
	}
	
	
	/**
     * 自定义实现负载均衡
     * 
     * 关键点：
     * 根据目标服务名获取所有实例，再随机抽取一个实例，并根据该实例的地址调用服务
	 * @param <T>
	 * @param serviceId
	 * @param uri
	 * @param clazz
	 * @return
	 */
	public <T> Object[] customLoadBalanced(String serviceId, String uri, Class<T> clazz) {
	    // 获取目标服务所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        // 随机策略实现负载均衡
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        // 获取实例地址
        String url = serviceInstance.getHost().concat(":").concat(String.valueOf(serviceInstance.getPort()));
        // 通过restTemplate调用目标服务
        RestTemplate restTemplate = new RestTemplate();
        T t = restTemplate.getForObject("http://".concat(url).concat(uri), clazz);
        return new Object[] { t, serviceInstance };
	}
	
	@Autowired
	RestTemplate ribbonRestTemplate;
	
	/**
	 * 基于Ribbon实现负载均衡
	 * @param <T>
	 * @param serviceId
	 * @param uri
	 * @param clazz
	 * @return
	 */
	public <T> Object[] ribbonLoadBalanced(String serviceId, String uri, Class<T> clazz) {
	    T t = ribbonRestTemplate.getForObject("http://".concat(serviceId).concat(uri), clazz);
	    return new Object[] { t, "ribbon" };
	}
	
}
