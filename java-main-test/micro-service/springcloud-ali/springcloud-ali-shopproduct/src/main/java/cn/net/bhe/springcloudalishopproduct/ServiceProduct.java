package cn.net.bhe.springcloudalishopproduct;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import cn.net.bhe.springcloudalicommon.bean.Product;

@Service
public class ServiceProduct {

	static final Logger log = LoggerFactory.getLogger(ServiceProduct.class);

	@Autowired
	DaoProduct daoProduct;

	public List<Map<String, Object>> test() {
		return daoProduct.test();
	}
	
	public Product productGetById(String id) {
	    return daoProduct.find(Product.class, id);
	}
	
	/**
	 * 初始化测试产品列表
	 */
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
    public void initProductList(SpringApplicationEvent event) {
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setId(String.valueOf(i));
            product.setName("产品".concat(String.valueOf(i)));
            product.setPrice(new Random().nextInt(100));
            daoProduct.merge(product);
        }
    }

}
