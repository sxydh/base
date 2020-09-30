package cn.net.bhe.utils.main;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum HttpClientUtils {
    ;
    static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    public static void main(String[] args) throws Exception {

    }

    public static String get(String url, Map<String, Object> paramMap) {
        String result = "";
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            String params = "";
            if (paramMap != null) {
                for (String key : paramMap.keySet()) {
                    params += "&" + key + "=" + paramMap.get(key);
                }
                params = "?" + params.substring(1);
            }
            HttpGet httpGet = new HttpGet(url + params);
            HttpResponse response = client.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            close(client);
        }
        return result;
    }
    
    public static String postWithJson(Map<String, Object> map, Object... objs) {
        StringBuilder url = new StringBuilder("http://");
        if (objs == null || objs.length == 0) {
            url.append("localhost:8080/");
        } else if (objs.length == 3) {
            url.append(objs[0]).append(':').append(objs[1]).append('/').append(objs[2]);
        } else if (objs.length == 2) {
            url.append("localhost:").append(objs[0]).append('/').append(objs[1]);
        } else if (objs.length == 1) {
            url.append("localhost:8080/").append(objs[0]);
        }
        return postWithJson(url.toString(), JsonUtils.string(map));
    }

    public static String postWithJson(String url, String jsonStr) {
        String result = "";
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if (StringUtils.isNotEmpty(jsonStr)) {
                StringEntity entity = new StringEntity(jsonStr, "UTF-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            HttpResponse response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            close(client);
        }
        return result;
    }

    public static String postWithForm(String url, Map<String, Object> formMap) {
        String result = "";
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if (formMap != null) {
                List<NameValuePair> form = new ArrayList<>();
                for (String key : formMap.keySet()) {
                    form.add(new BasicNameValuePair(key, formMap.get(key).toString()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, "UTF-8");
                httpPost.setEntity(entity);
            }
            ResponseHandler<String> responseHandler = handler -> {
                HttpEntity responseEntity = handler.getEntity();
                return EntityUtils.toString(responseEntity);
            };
            result = client.execute(httpPost, responseHandler);
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            close(client);
        }
        return result;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
