package cn.net.bhe.utils.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum HttpAsyncClientUtils {

    ;

    static Logger LOGGER = LoggerFactory.getLogger(HttpAsyncClientUtils.class);

    private static CloseableHttpAsyncClient client;

    private static int connectTimeout = 50000;
    private static int socketTimeout = 50000;
    private static int requestTimeout = 20;

    private static int maxTotal = 1;
    private static int maxPerRoute = 1;

    static {
        RequestConfig requestConfig = RequestConfig.custom()
                //
                .setConnectTimeout(connectTimeout)
                //
                .setSocketTimeout(socketTimeout)
                //
                .setConnectionRequestTimeout(requestTimeout)
                //
                .build();

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                //
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                //
                .setSoKeepAlive(true)
                //
                .build();

        ConnectingIOReactor ioReactor = null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(maxTotal);
        connManager.setDefaultMaxPerRoute(maxPerRoute);

        client = HttpAsyncClients.custom()
                //
                .setConnectionManager(connManager)
                //
                .setDefaultRequestConfig(requestConfig)
                //
                .build();

        client.start();
    }

    public static String postWithJson(String url, String jsonStr) throws Exception {
        String response = "";

        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotEmpty(jsonStr)) {
            StringEntity se = new StringEntity(jsonStr, Consts.UTF_8);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");
            post.setEntity(se);
        }

        HttpResponse rs = client.execute(post, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(HttpResponse result) {
            }

            @Override
            public void failed(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {

            }
        }).get();

        int status = rs.getStatusLine().getStatusCode();
        LOGGER.info("response status: " + status);
        if (status == HttpStatus.SC_OK) {
            response = EntityUtils.toString(rs.getEntity());
        } else {
            throw new ClientProtocolException(String.valueOf(status));
        }

        return response;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "15112344321");
        map.put("pwd", "21232f297a57a5a743894a0e4a801fc3");

        String login = HttpAsyncClientUtils.postWithJson("http://localhost:9900/tout/dispatch/login", JacksonUtils.objToJsonStr(map));
        System.out.println(login);
    }
}
