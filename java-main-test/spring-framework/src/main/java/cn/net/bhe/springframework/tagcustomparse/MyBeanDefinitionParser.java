package cn.net.bhe.springframework.tagcustomparse;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class MyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected Class<?> getBeanClass(Element element) {
        try {
            return Class.forName(element.getAttribute("class"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        // this will never be null since the schema explicitly requires that a value be supplied
        String pattern = element.getAttribute("pattern");
        builder.addConstructorArgValue(pattern);

        // this however is an optional property
        String lenient = element.getAttribute("lenient");
        if (StringUtils.hasText(lenient)) {
            builder.addPropertyValue("lenient", Boolean.valueOf(lenient));
        }
    }
}
