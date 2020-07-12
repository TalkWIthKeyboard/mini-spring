package io.talkwithkeyboard.code.bean;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory {

    private final static Map<String, BeanDefinition> registry = new HashMap<>();

    @Override
    public Object getBean(String name) throws Exception {
        var beanDefinition = registry.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        var bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreate(beanDefinition);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition)
        throws Exception {
        var bean = doCreate(beanDefinition);
        beanDefinition.setBean(bean);
        registry.put(name, beanDefinition);
    }

    /**
     * 通过beanDefinition的描述信息创建真实的bean
     */
    abstract Object doCreate(BeanDefinition beanDefinition) throws Exception;
}
