# Profile
Spring Cloud Alibaba致力于提供微服务开发的一站式解决方案，包含开发分布式应用微服务的必需组件，方便开发者通过Spring Cloud编程模型轻松使用这些组件来开发分布式应用服务。

# Component
Spring Cloud Alibaba的主要组件
  * Nacos。一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。
  * Sentinel。把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。
  * Seata。阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。
  * RocketMQ。一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。
  * Alibaba Cloud SchedulerX。阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于Cron表达式）任务调度服务。
  * Alibaba Cloud SMS。覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。
  * Alibaba Cloud OSS。阿里云对象存储服务（Object Storage Service，简称OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。
  * Alibaba Cloud ACM。一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。
  * Dubbo。Apache Dubbo™是一款高性能Java RPC框架。

# Features
微服务应当具备的核心功能和Spring Cloud Alibaba的对应实现
  * 服务治理
    * 基于Nacos
  * 服务调用
    * 基于Feign
    * 基于RestTemplate
  * 服务网关
    * 基于Spring Cloud Gateway
  * 服务容错
    * 基于Sentinel
  * 链路追踪
    * SpringCloud Alibaba技术栈中并没有提供自己的链路追踪技术的，我们可以采用Sleuth和Zinkin来做链路追踪解决方案。

# 服务治理
  * 环境准备
    * Nacos安装  
      Nacos是一个注册中心，其核心作用就是服务发现和管理。
      * [*下载*](https://github.com/alibaba/nacos/releases)
      * 启动Nacos：```./bin/startup.cmd```，有可能最新版本不是最适配的，若启动出错可尝试更换旧一点的版本
      * 访问[*控制台*](http://127.0.0.1:8848/nacos/index.html)，默认账号和密码都是nacos
  * 服务创建
    * 创建[*shopuser*](./springcloud-ali-shopuser)服务  
      通过配置文件和注解将服务注册到Nacos
      ```properties
      # 服务名
      spring.application.name=service-shopuser
      # 注册中心地址
      spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
      # Nacos账号和密码，默认都是nacos
      spring.cloud.nacos.username=nacos
      spring.cloud.nacos.password=nacos

      ```
      ```java
      @EnableDiscoveryClient	// 开启服务注册与发现功能
      @SpringBootApplication
      public class __SpringCloudAliShopUser__ {
      	public static void main(String[] args) {
      		SpringApplication.run(__SpringCloudAliShopUser__.class, args);
      	}
      }
      ```
    * 同理创建[*shopproduct*](./springcloud-ali-shopproduct)、[*shoporder*](./springcloud-ali-shoporder)服务
    * 此时[*Nacos控制台*](http://127.0.0.1:8848/nacos/index.html)可以看到相对应的服务
# 服务调用
  * [*自定义实现负载均衡*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/ControllerOrder.java)
    * DiscoveryClient获取目标服务的所有实例
    * 随机选用其中一个实例
    * 获取实例的地址
    * 使用RestTemplate手动调用
  * [*基于Ribbon实现负载均衡*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/__SpringCloudAliShopOrder__.java)
    * RestTemplate的生成方法上加入@LoadBalanced即可
    * 可以通过编辑配置文件来设置均衡策略
      ```properties
      service-shopproduct.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RoundRobinRule
      ```
  * [*基于Feign的服务调用*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/__SpringCloudAliShopOrder__.java)。Feign是Spring Cloud提供的一个声明式的伪Http客户端，它使得调用远程服务就像调用本地服务一样简单，只需要创建一个接口并添加一个注解即可。Nacos很好的兼容了Feign，Feign默认集成了Ribbon，所以在Nacos下使用Fegin默认就实现了负载均衡的效果。
    * 主类上添加注解```@EnableFeignClients```
    * 创建一个service，并使用Fegin实现微服务调用
      ```java
      @FeignClient("service-shopproduct") // 声明服务提供者的name
      public interface ProductService {
          // 指定服务提供者的哪个方法
          // @FeignClient + @GetMapping就是一个完整的请求路径http://service-shopproduct/product/{pid}
          @GetMapping(value = "/product/{pid}")
          Product productGetById(@PathVariable("pid") String pid);
      }
      ```
    * 调用
      ```java
      @Autowired  
      ProductService productService;
      @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = { "/order/product/{pid}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
      public Object order(HttpServletRequest httpServletRequest, @PathVariable(required = true) String pid) {
          productService.productGetById(pid);
          // ......
      }
      ```
# 服务容错
常见的容错方案有隔离、超时、熔断、降级、限流等。  
Spring Cloud Alibaba容错基于Sentinel实现。
Sentinel（分布式系统的流量防卫兵）是阿里开源的一套用于服务容错的综合性解决方案。它以流量为切入点, 从流量控制、熔断降级、系统负载保护等多个维度来保护服务的稳定性。  
资源是Sentinel的关键概念。它可以是Java应用程序中的任何内容，可以是一个服务，也可以是一个方法，甚至可以是一段代码。
  * 集成Sentinel
    ```xml
		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
		</dependency>
    ```
  * Sentinel的控制台
    * [*下载*](https://github.com/alibaba/Sentinel/releases)
    * 微服务加入有关sentinel控制台的配置
      ```properties
      # 跟sentinel控制台通信地址，随意指定一个未占用的端口即可
      spring.cloud.sentinel.transport.port=33562
      # sentinel控制台服务地址
      spring.cloud.sentinel.transport.dashboard=localhost:8080
      ```
    * 启动控制台（控制台本身是一个SpringBoot项目，不建议用win10的Powershell）。  
    [*访问*](localhost:8080)，默认账号密码都是sentinel。  
    若控制台看不到已启动的微服务，可以尝试先访问微服务任意接口几次，再刷新控制台。
      ```cmd
      java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar
      ```
    * 控制台的使用
      * 流控/降级/热点/授权/系统：略
  * [*自定义快速失败后的返回信息*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/__SpringCloudAliShopOrder__java)
  * [*注解@SentinelResource*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/ServiceOrder.java)  
  可以增加资源控制点，并指定失败后的处理方法或异常的处理方法。注意：回调方法应当具有和原方法一致的参数类型和返回类型。
  * Sentinel规则持久化  
  Sentinel控制台通过API将规则推送至客户端并更新到内存中，接着注册的写数据源会将新的规则保存到本地的文件中。
    * 实现[*处理类*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/__SpringCloudAliShopOrder__.java)
    * 添加配置文件
      * resources/META-INF/services下添加文件```FileDataSourceInit```
      * ```FileDataSourceInit```内添加处理类的全路径```cn.net.bhe.springcloudalishoporder.__SpringCloudAliShopOrder__$FileDataSourceInit```
  * Feign整合Sentinel
    * 添加依赖
      ```xml
		  <dependency>
		  	<groupId>com.alibaba.cloud</groupId>
		  	<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
		  </dependency>
      ```
    * 配置中开启Feign对Sentinel的支持
      ```properties
      feign.sentinel.enabled=true
      ```
    * 实现[*容错类*](./springcloud-ali-shoporder/src/main/java/cn/net/bhe/springcloudalishoporder/__SpringCloudAliShopOrder__.java)
    * @FeignClient指定容错类
      ```java
      @FeignClient(
              value = "service-shopproduct", // 声明服务提供者的name
              fallback = ProductServiceFallback.class) // 指定容错类
      public interface ProductService {
          // 指定服务提供者的哪个方法
          // @FeignClient + @GetMapping就是一个完整的请求路径http://service-shopproduct/product/{pid}
          @GetMapping(value = "/product/{pid}")
          Product productGetById(@PathVariable("pid") String pid);
      }
      ```        
    * 关闭product微服务，调用订单微服务的下单接口，查看效果
# 服务网关
  * 为什么需要网关  
  如果没有网关的存在，我们只能在客户端记录每个微服务的地址，然后分别去调用。这样的架构，会存在着诸多的问题
    * 客户端多次请求不同的微服务，增加客户端代码或配置编写的复杂性
    * 认证复杂，每个服务都需要独立认证
    * 存在跨域请求，在一定场景下处理相对复杂  
  * [*基本实现*](./springcloud-ali-gateway)  
  Spring Cloud Alibaba技术栈中并没有提供自己的网关，我们可以采用Spring Cloud Gateway来做网关。
    * 添加依赖，若启动出错可以尝试更换gateway版本
      ```xml
		  <dependency>
		  	<groupId>com.alibaba.cloud</groupId>
		  	<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
		  </dependency>
		  <dependency>
		  	<groupId>org.springframework.cloud</groupId>
		  	<artifactId>spring-cloud-starter-gateway</artifactId>
		  </dependency>
      ```
    * [*配置文件*](./springcloud-ali-gateway/src/main/resources/application.yml)
    * 启动本程序和shopproduct微服务
    * [*调用*](http://127.0.0.1:18041/service-shopproduct/product/1)
# 链路追踪
分布式链路追踪（Distributed Tracing），就是将一次分布式请求还原成调用链路，进行日志记录，性能监控并将一次分布式请求的调用情况集中展示。比如各个服务节点上的耗时、请求具体到达哪台机器上、每个服务节点的请求状态等等。  
Spring Cloud Alibaba技术栈中并没有提供自己的链路追踪技术的，我们可以采用Sleuth + Zinkin来做链路追踪解决方案。  
SpringCloud Sleuth的主要作用是生成链路追踪信息。  
Zipkin的主要作用是数据的收集、存储、查找和展现。  
  * Sleuth的简单使用
    * 添加依赖（父工程统一添加）
      ```xml
		  <dependency>
		  	<groupId>org.springframework.cloud</groupId>
		  	<artifactId>spring-cloud-starter-sleuth</artifactId>
		  </dependency>
      ```
    * 启动服务并调用，查看日志链路信息（日志级别trace）
  * 集成Zipkin
    * [*下载服务端*](https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server)
    * 启动服务端
      ```cmd
      java -jar zipkin-server-2.12.9-exec.jar
      ```
    * 查看[*Zipkin控制台*](http://127.0.0.1:9411/)
    * 客户端集成
      * 添加依赖（父工程统一添加）
        ```xml
		    <dependency>
		    	<groupId>org.springframework.cloud</groupId>
		    	<artifactId>spring-cloud-starter-zipkin</artifactId>
		    </dependency>
        ```
      * [*配置文件*](./springcloud-ali-gateway/src/main/resources/application.yml)
        ```properties
        spring:
          zipkin:
            # zipkin server的请求地址
            bash-url: http://127.0.0.1:9411/
            # 让nacos把它当成一个URL，而不要当做服务名
            discoveryClientEnabled: false
          sleuth:
            sampler:
              # 采样的百分比
              probability: 1.0
        ```
      * 通过网关调用微服务并查看控制台信息
# 其他
  * RocketMQ  
  MQ（Message Queue）是一种跨进程的通信机制，用于传递消息。通俗点说，就是一个先进先出的数据结构。  
  消息队列的主要作用是异步解耦、流量削峰。  
  RocketMQ可以发送三种普通消息类型：同步发送、异步发送、单向发送。除了普通消息之外还有：顺序消息、事务消息等。  
    * RocketMQ服务端安装
      * 安装JDK8（略）
      * [*下载*](https://rocketmq.apache.org/dowloading/releases/)RocketMQ（本次测试下载的是Binary）
      * 解压
      * 启动NameServer（可能需要事先执行```chmod -R 777 /root/RocketMQ/rocketmq-all-4.7.1-bin-release/```，否则nohup出现Permission denied）
        ```bash
        nohup ./bin/mqnamesrv &
        tail -f /root/logs/rocketmqlogs/namesrv.log # 查看启动日志
        ```
      * 启动Broker  
        ```bash
        # 预先编辑bin/runbroker.sh和bin/runserver.sh文件，修改里面的
        # JAVA_OPT="${JAVA_OPT} -server -Xms8g -Xmx8g -Xmn4g"
        # 为JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m"

        nohup bin/mqbroker -n localhost:9876 &
        tail -f /root/logs/rocketmqlogs/broker.log # 查看启动日志
        ```
      * 测试消息发送
        ```bash
        export NAMESRV_ADDR=localhost:9876
        bin/tools.sh org.apache.rocketmq.example.quickstart.Producer # 成功后应该会出现sendStatus=SEND_OK等信息
        ```
      * 测试消息接收
        ```bash
        export NAMESRV_ADDR=localhost:9876
        bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
        ```
      * 关闭
        ```bash
        bin/mqshutdown broker
        bin/mqshutdown namesrv
        ```
    * RocketMQ控制台
      * [*下载*](https://github.com/apache/rocketmq-externals/releases)rocketmq-console
      * 修改配置文件
        ```properties
        # 修改配置文件rocketmq-console\src\main\resources\application.properties
        server.port=7777 # 项目启动后的端口号
        rocketmq.config.namesrvAddr=192.168.109.131:9876 # nameserv的地址，注意防火墙要开启9876端口
        ```
      * 打包jar```mvn clean package -Dmaven.test.skip=true```
      * 启动控制台```java -jar target/rocketmq-console-ng-1.0.0.jar```
      * [*访问*](http://127.0.0.1:7777/)控制台
    * 集成
      * shoporder和shopuser微服务添加依赖
        ```xml
		    <dependency>
		    	<groupId>org.apache.rocketmq</groupId>
		    	<artifactId>rocketmq-client</artifactId>
		    	<version>4.4.0</version>
		    </dependency>
		    <dependency>
		    	<groupId>org.apache.rocketmq</groupId>
		    	<artifactId>rocketmq-spring-boot-starter</artifactId>
		    	<version>2.0.2</version>
		    </dependency>
        ```
      * 配置文件
        * shoporder
          ```properties
          # RocketMQ服务的地址
          rocketmq.name-server=192.168.145.128:9876
          # 生产者组
          rocketmq.producer.group=shoporder
          ```
        * shopuser
          ```properties
          # RocketMQ服务的地址
          rocketmq.name-server=192.168.145.128:9876
          ```
      * 消息收发
        * shoporder
          ```java
          // 下单成功后发送消息
          rocketMQTemplate.convertAndSend("topic-order", order);
          ```
        * shopuser
          ```java
          @Component
          @RocketMQMessageListener(consumerGroup = "shopuser", topic = "topic-order")
          public static class SmsService implements RocketMQListener<Order> {
              @Override
              public void onMessage(Order order) {
                  log.info("收到消息：{}", JsonUtils.string(order));
              }
          }
          ```
      
        