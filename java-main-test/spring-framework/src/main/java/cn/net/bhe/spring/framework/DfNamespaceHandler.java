package cn.net.bhe.spring.framework;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class DfNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("dateformat", new DfBeanDefinitionParser());
    }
}