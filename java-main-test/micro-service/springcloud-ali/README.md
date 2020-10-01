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
      * [*控制台*](http://127.0.0.1:8848/nacos/index.html)可以查看服务等信息
    * 创建shopuser服务  
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