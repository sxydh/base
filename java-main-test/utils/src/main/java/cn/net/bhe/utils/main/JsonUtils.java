package cn.net.bhe.utils.main;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

public enum JsonUtils {
    ;
    static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static ValueFilter valueFilter = new ValueFilter() {
        @Override
        public Object process(Object object, String name, Object value) {
            if (value == null) {
                return "";
            } else if (value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof BigDecimal) {
                return value.toString();
            }
            return value;
        }
    };

    public static String string(Object obj) {
        return JSON.toJSONString(obj, valueFilter);
    }
    
    public static <T> T parse(String string, Class<T> clazz) {
        return JSONObject.parseObject(string, clazz);
    }

}
