package cn.net.bhe.basics.net.httpClient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientTest {
    static Logger log = LoggerFactory.getLogger(HttpClientTest.class);

    /**
     * GET简单实现
     */
    @Test
    public void get() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet("http://www.baidu.com");
            HttpResponse rs = client.execute(get);
            int status = rs.getStatusLine().getStatusCode();
            log.info("状态：{}", status);
            if (status == HttpStatus.SC_OK) {
                log.info("响应：{}", EntityUtils.toString(rs.getEntity()));
            } else {
                log.error("", new ClientProtocolException(String.valueOf(status)));
            }
            client.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * POST Json简单实现
     */
    @Test
    public void postWithJson() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost("http://www.baidu.com");
            StringEntity se = new StringEntity("{}", Consts.UTF_8);
            se.setContentEncoding(Consts.UTF_8.toString());
            se.setContentType("application/json");
            post.setEntity(se);
            HttpResponse rs = client.execute(post);
            int status = rs.getStatusLine().getStatusCode();
            log.info("状态：{}", status);
            if (status == HttpStatus.SC_OK) {
                log.info("响应：{}", EntityUtils.toString(rs.getEntity()));
            } else {
                log.error("", new ClientProtocolException(String.valueOf(status)));
            }
            client.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * POST Form简单实现
     */
    @Test
    public void postWithForm() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost("http://www.baidu.com");
            List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair("p1", "p1"));
            form.add(new BasicNameValuePair("p2", "p2"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
            post.setEntity(entity);
            ResponseHandler<String> responseHandler = handler -> {
                int status = handler.getStatusLine().getStatusCode();
                log.info("状态：{}", status);
                if (status == HttpStatus.SC_OK) {
                    log.info("响应：{}", EntityUtils.toString(handler.getEntity()));
                } else {
                    log.error("", new ClientProtocolException(String.valueOf(status)));
                }
                return "";
            };
            client.execute(post, responseHandler);
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
