package cn.net.bhe.springcloudalishoporder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import cn.net.bhe.springcloudalicommon.bean.Order;
import cn.net.bhe.springcloudalicommon.bean.Product;
import cn.net.bhe.springcloudalishoporder.__SpringCloudAliShopOrder__.ProductService;

@Service
public class ServiceOrder {
	
	static final Logger log = LoggerFactory.getLogger(ServiceOrder.class);
	
	@Autowired
	DaoOrder daoOrder;
	
	/*
	 * 回调方法应当具有和原方法一致的返回类型
	 */
	@SentinelResource(
	        value = "test",
	        blockHandler = "blockHandlerForTest", // 指定发生BlockException时进入的方法
	        fallback = "fallbackForTest") // 指定发生Throwable时进入的方法
	public List<Map<String, Object>> test() {
	    if (new Random().nextInt(2) == 1) {
	        throw new RuntimeException("随机异常");
	    }
		return daoOrder.test();
	}
	
	public List<Map<String, Object>> blockHandlerForTest(BlockException e) {
	    log.info("{}", e.getLocalizedMessage());
	    return new ArrayList<Map<String, Object>>();    
	}
	
	public List<Map<String, Object>> fallbackForTest(Throwable throwable) {
	    log.info("{}", throwable.getLocalizedMessage());
	    return new ArrayList<Map<String, Object>>();  
	}
	
	@Autowired
	ProductService productService;
	
	@Transactional // 非全局事务
	public Order order(Product product) {
	    // 保存订单
        Order order = new Order();
        order.setUsrid("1");
        order.setUsrname("测试用户");
        order.setPid(product.getId());
        order.setPname(product.getName());
        order.setPprice(product.getPrice());
        order.setNum(1);
        daoOrder.merge(order);
        
        // 减库存
        productService.productReduce(product.getId(), 1);
        return order;
	}
	
	@Transactional
	public void merge(Object obj) {
		daoOrder.merge(obj);
	}

}
