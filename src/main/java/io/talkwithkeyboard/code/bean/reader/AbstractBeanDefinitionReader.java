package io.talkwithkeyboard.code.bean.reader;

import io.talkwithkeyboard.code.bean.BeanDefinition;
import io.talkwithkeyboard.code.bean.resource.ResourceLoader;
import java.util.HashMap;
import java.util.Map;

public class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final Map<String, BeanDefinition> registry;

    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
