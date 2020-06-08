package cn.net.bhe.basics.net.httpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.utils.main.JacksonUtils;

/**
 * 异步请求
 */
public class HttpAsyncClientTest {
    static final Logger log = LoggerFactory.getLogger(HttpAsyncClientTest.class);
    
    /**
     * 简单例子
     * 参考https://blog.csdn.net/ouyang111222/article/details/78884634
     */
    @Test
    public void example() throws Exception {
        // 请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000) // 连接超时，连接建立时间，三次握手完成时间
                .setSocketTimeout(50000) // 请求超时，数据传输过程中数据包之间间隔的最大时间
                // 从connection manager获得connection最大等待时间，0是无限制等待
                // https://stackoverflow.com/questions/31611861/why-setconnectionrequesttimeout-doesnt-stop-my-1-min-get-request
                // https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/client/config/RequestConfig.html
                .setConnectionRequestTimeout(0)
                .build();

        // 连接池配置
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(
                new DefaultConnectingIOReactor(ioReactorConfig));
        connManager.setMaxTotal(20); // 连接池中最大连接数
        // 分配给同一个route(路由)最大的并发连接数
        // route为运行环境机器到目标机器的一条线路，举例来说，我们使用HttpClient的实现来分别请求 www.baidu.com 的资源和 www.bing.com 的资源那么他就会产生两个route
        connManager.setDefaultMaxPerRoute(5); 

        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // 构造多个请求
        String url = "http://www.baidu.com";
        List<HttpPost> httpPosts = new ArrayList<HttpPost>();
        for (int i = 0; i < 2; i++) {
            HttpPost httpPost = new HttpPost(url);
            Map<String, String> map = new HashMap<>();
            map.put("p1", "p1");
            map.put("p2", "p2");
            StringEntity entity = new StringEntity(JacksonUtils.objToJsonStr(map), Consts.UTF_8);
            entity.setContentEncoding(Consts.UTF_8.toString());
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPosts.add(httpPost);
        }

        client.start();

        for (HttpPost httpPost : httpPosts) {
            client.execute(httpPost, new CallBac());
        }
        
        Thread.currentThread().join();
    }

    static class CallBac implements FutureCallback<HttpResponse> {
        private long start = System.currentTimeMillis();
        CallBac() {}

        @Override
        public void completed(HttpResponse httpResponse) {
            try {
                log.info("\r\n耗时：{}，响应：{}", System.currentTimeMillis() - start, EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Exception e) {
            log.info("\r\n耗时：{}，响应：{}", System.currentTimeMillis() - start, e.getLocalizedMessage());
        }

        @Override
        public void cancelled() {

        }
    }
}
