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
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import cn.net.bhe.utils.main.JacksonUtils;

public class Async {
    public static void main(String[] args) {

        RequestConfig requestConfig = RequestConfig.custom()
                //
                .setConnectTimeout(50000)
                //
                .setSocketTimeout(50000)
                //
                .setConnectionRequestTimeout(20)
                //
                .build();

        // 配置IO线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                //
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                //
                .setSoKeepAlive(true)
                //
                .build();

        // 设置连接池大小
        ConnectingIOReactor ioReactor = null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(1); // 最大连接数设置1
        connManager.setDefaultMaxPerRoute(1); // per route最大连接数设置1

        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                //
                .setConnectionManager(connManager)
                //
                .setDefaultRequestConfig(requestConfig)
                //
                .build();

        // 构造请求
        String url = "http://localhost:9900/tout/dispatch/login";
        List<HttpPost> list = new ArrayList<HttpPost>();
        for (int i = 0; i < 2; i++) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = null;
            try {
                Map<String, String> map = new HashMap<>();
                map.put("username", "15112344321");
                map.put("pwd", "21232f297a57a5a743894a0e4a801fc3");
                String str = JacksonUtils.objToJsonStr(map);
                entity = new StringEntity(str, Consts.UTF_8);
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
            list.add(httpPost);
        }

        client.start();

        for (int i = 0; i < 2; i++) {
            client.execute(list.get(i), new CallBac());
        }

    }

    static class CallBac implements FutureCallback<HttpResponse> {

        private long start = System.currentTimeMillis();

        CallBac() {

        }

        @Override
        public void completed(HttpResponse httpResponse) {
            try {
                String str = "";
                str += "\n" + Thread.currentThread().getStackTrace()[1].getMethodName();
                str += "\n" + (System.currentTimeMillis() - start);
                str += "\n" + EntityUtils.toString(httpResponse.getEntity());
                str += "\n";
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Exception e) {
            String str = "";
            str += "\n" + Thread.currentThread().getStackTrace()[1].getMethodName();
            str += "\n" + (System.currentTimeMillis() - start);
            str += "\n";
            System.out.println(str);
            e.printStackTrace();
        }

        @Override
        public void cancelled() {

        }
    }
}
