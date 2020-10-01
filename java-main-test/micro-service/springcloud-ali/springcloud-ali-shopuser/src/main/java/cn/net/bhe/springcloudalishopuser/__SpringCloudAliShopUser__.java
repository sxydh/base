package cn.net.bhe.springcloudalishopuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 概述：
 * 利用nacos的服务发现功能将本地服务注册到nacos，供其它consumer使用。
 * 
 * 关键点：
 * 1、注解显示开启服务发现
 * @EnableDiscoveryClient
 * 
 * 2、配置服务名和Nacos地址
 * application.properties
 *     # 服务名
 *     spring.application.name=service-shopuser
 *     # 注册中心地址
 *     spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
 *     # Nacos账号和密码，默认都是nacos
 *     spring.cloud.nacos.username=nacos
 *     spring.cloud.nacos.password=nacos
 */
@EnableDiscoveryClient	// 开启Nacos的服务发现
@SpringBootApplication
public class __SpringCloudAliShopUser__ {

	public static void main(String[] args) {
		SpringApplication.run(__SpringCloudAliShopUser__.class, args);
	}

}
