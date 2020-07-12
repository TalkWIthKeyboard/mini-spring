package io.talkwithkeyboard.code.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {

    private Object bean;

    private Class beanClass;

    private String className;

    private List<PropertyValue> propertyValues;

    public BeanDefinition() {
        this.propertyValues = new ArrayList<>();
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setClassName(String className) {
        this.className = className;
        try {
            this.beanClass = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static class PropertyValue {

        private final String key;

        private final Object value;

        public PropertyValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
