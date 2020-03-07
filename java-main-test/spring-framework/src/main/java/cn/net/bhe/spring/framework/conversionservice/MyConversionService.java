package cn.net.bhe.spring.framework.conversionservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

public class MyConversionService implements ConversionService {
    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        if (Date.class.isAssignableFrom(targetType)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (Date.class.isAssignableFrom(targetType.getObjectType())) {
            return true;
        }
        return false;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (String.class.isAssignableFrom(sourceType.getObjectType())) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return format.parse((String) source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
