package cn.net.bhe.springframework.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

}
