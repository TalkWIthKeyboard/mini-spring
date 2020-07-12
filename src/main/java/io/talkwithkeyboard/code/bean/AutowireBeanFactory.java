package io.talkwithkeyboard.code.bean;

public class AutowireBeanFactory extends AbstractBeanFactory {

    @Override
    Object doCreate(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
        addPropertyValue(bean, beanDefinition);
        return bean;
    }

    /**
     * 给定bean和描述的属性值，通过注入初始化bean的值
     */
    private void addPropertyValue(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (var propertyValue : beanDefinition.getPropertyValues()) {
            var declaredField = bean.getClass().getDeclaredField(propertyValue.getKey());
            declaredField.setAccessible(true);
            var value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                var beanRef = (BeanReference) value;
                value = getBean(beanRef.getName());
            }
            declaredField.set(bean, value);
        }
    }
}
