package cn.net.bhe.spring.framework.tagcustomparse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("mybean", new MyBeanDefinitionParser());
    }
}