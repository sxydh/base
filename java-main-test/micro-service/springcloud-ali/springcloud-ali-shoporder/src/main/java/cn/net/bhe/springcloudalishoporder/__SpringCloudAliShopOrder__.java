package cn.net.bhe.springcloudalishoporder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.net.bhe.springcloudalicommon.bean.Product;
import cn.net.bhe.utils.main.JsonUtils;
import feign.hystrix.FallbackFactory;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients // 开启Feign
@EntityScan("cn.net.bhe.springcloudalicommon.bean")
public class __SpringCloudAliShopOrder__ {
    
    static final Logger log = LoggerFactory.getLogger(__SpringCloudAliShopOrder__.class);

    public static void main(String[] args) {
        SpringApplication.run(__SpringCloudAliShopOrder__.class, args);
    }

    /*
     * Ribbon是Spring Cloud的一个组件，它可以让我们使用一个注解就能轻松的搞定负载均衡
     */
    @Bean
    @LoadBalanced
    public RestTemplate ribbonRestTemplate() {
        return new RestTemplate();
    }
    
    @FeignClient(
            value = "service-shopproduct", // 声明服务提供者的name
            fallbackFactory = ProductServiceFallback.class) // 指定容错类
    public interface ProductService {
        // 指定服务提供者的哪个方法
        // @FeignClient + @GetMapping就是一个完整的请求路径http://service-shopproduct/product/{pid}
        @GetMapping(value = "/product/{pid}") // 获取产品信息
        Product productGetById(@PathVariable("pid") String pid);
        
        @GetMapping(value = "/product/reduce/{pid}/{num}") // 减库存
        Product productReduce(@PathVariable("pid") String pid, @PathVariable("num") int num);
    }

    /*
     * 自定义sentinel快速失败后的返回信息
     */
    @Component
    public class ExceptionHandlerPage implements BlockExceptionHandler {
        // BlockException 异常接口，包含Sentinel的五个异常
        // FlowException 限流异常
        // DegradeException 降级异常
        // ParamFlowException 参数限流异常
        // AuthorityException 授权异常
        // SystemBlockException 系统负载异常
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
            response.setContentType("application/json;charset=utf-8");
            String msg = "";
            if (e instanceof FlowException) {
                msg = "接口被限流了...";
            } else if (e instanceof DegradeException) {
                msg = "接口被限流了...";
            }
            Map<String, Object> ret = new HashMap<String, Object>();
            ret.put("code", "300");
            ret.put("msg", msg);
            response.getWriter().write(JsonUtils.string(ret));
        }
    }
    
    /*
     * Sentinel规则持久化实现类
     */
    @Component
    public static class FileDataSourceInit implements InitFunc {
        
        @Override
        public void init() throws Exception {
            String ruleDir = System.getProperty("user.home") + "/sentinel/rules/service-shoporder";
            String flowRulePath = ruleDir + "/flow-rule.json";
            String degradeRulePath = ruleDir + "/degrade-rule.json";
            String systemRulePath = ruleDir + "/system-rule.json";
            String authorityRulePath = ruleDir + "/authority-rule.json";
            String paramFlowRulePath = ruleDir + "/param-flow-rule.json";

            this.mkdirIfNotExits(ruleDir);
            this.createFileIfNotExits(flowRulePath);
            this.createFileIfNotExits(degradeRulePath);
            this.createFileIfNotExits(systemRulePath);
            this.createFileIfNotExits(authorityRulePath);
            this.createFileIfNotExits(paramFlowRulePath);

            // 流控规则
            ReadableDataSource<String, List<FlowRule>> flowRuleRDS = new FileRefreshableDataSource<>(flowRulePath, flowRuleListParser);
            // 将可读数据源注册至FlowRuleManager
            // 这样当规则文件发生变化时，就会更新规则到内存
            FlowRuleManager.register2Property(flowRuleRDS.getProperty());
            WritableDataSource<List<FlowRule>> flowRuleWDS = new FileWritableDataSource<>(flowRulePath, this::encodeJson);
            // 将可写数据源注册至transport模块的WritableDataSourceRegistry中
            // 这样收到控制台推送的规则时，Sentinel会先更新到内存，然后将规则写入到文件中
            WritableDataSourceRegistry.registerFlowDataSource(flowRuleWDS);

            // 降级规则
            ReadableDataSource<String, List<DegradeRule>> degradeRuleRDS = new FileRefreshableDataSource<>(degradeRulePath, degradeRuleListParser);
            DegradeRuleManager.register2Property(degradeRuleRDS.getProperty());
            WritableDataSource<List<DegradeRule>> degradeRuleWDS = new FileWritableDataSource<>(degradeRulePath, this::encodeJson);
            WritableDataSourceRegistry.registerDegradeDataSource(degradeRuleWDS);

            // 系统规则
            ReadableDataSource<String, List<SystemRule>> systemRuleRDS = new FileRefreshableDataSource<>(systemRulePath, systemRuleListParser);
            SystemRuleManager.register2Property(systemRuleRDS.getProperty());
            WritableDataSource<List<SystemRule>> systemRuleWDS = new FileWritableDataSource<>(systemRulePath, this::encodeJson);
            WritableDataSourceRegistry.registerSystemDataSource(systemRuleWDS);

            // 授权规则
            ReadableDataSource<String, List<AuthorityRule>> authorityRuleRDS = new FileRefreshableDataSource<>(authorityRulePath, authorityRuleListParser);
            AuthorityRuleManager.register2Property(authorityRuleRDS.getProperty());
            WritableDataSource<List<AuthorityRule>> authorityRuleWDS = new FileWritableDataSource<>(authorityRulePath, this::encodeJson);
            WritableDataSourceRegistry.registerAuthorityDataSource(authorityRuleWDS);

            // 热点参数规则
            ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleRDS = new FileRefreshableDataSource<>(paramFlowRulePath, paramFlowRuleListParser);
            ParamFlowRuleManager.register2Property(paramFlowRuleRDS.getProperty());
            WritableDataSource<List<ParamFlowRule>> paramFlowRuleWDS = new FileWritableDataSource<>(paramFlowRulePath, this::encodeJson);
            ModifyParamFlowRulesCommandHandler.setWritableDataSource(paramFlowRuleWDS);
        }

        private Converter<String, List<FlowRule>> flowRuleListParser = source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
        });
        private Converter<String, List<DegradeRule>> degradeRuleListParser = source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
        });
        private Converter<String, List<SystemRule>> systemRuleListParser = source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {
        });

        private Converter<String, List<AuthorityRule>> authorityRuleListParser = source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
        });

        private Converter<String, List<ParamFlowRule>> paramFlowRuleListParser = source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
        });

        private void mkdirIfNotExits(String filePath) throws IOException {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        private void createFileIfNotExits(String filePath) throws IOException {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        }

        private <T> String encodeJson(T t) {
            return JSON.toJSONString(t);
        }
    }
    
    /*
     * Feign整合Sentinel后的容错类
     */
    @Component
    public class ProductServiceFallback implements FallbackFactory<ProductService> {

        @Override
        public ProductService create(Throwable cause) {
            return new ProductService() {
                @Override
                public Product productGetById(String pid) {
                    log.error("", cause);
                    Product product = new Product();
                    return product;
                }

                @Override
                public Product productReduce(String pid, int num) {
                    log.error("", cause);
                    Product product = new Product();
                    product.setId("-1");
                    return product;
                }
            };
        }
        
    }
    
}
