package io.talkwithkeyboard.code.bean;

public interface BeanFactory {

    /**
     * 通过名字获取bean
     */
    Object getBean(String name) throws Exception;

    /**
     * 将bean注册到容器中
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
