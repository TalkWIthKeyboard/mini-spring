package io.talkwithkeyboard.code.bean.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.talkwithkeyboard.code.bean.resource.ResourceLoader;
import org.junit.jupiter.api.Test;

public class XmlBeanDefinitionReaderTest {

    @Test
    void testReadXML() throws Exception {
        var resourceLoader = new ResourceLoader();
        var xmlReader = new XmlBeanDefinitionReader(resourceLoader);
        xmlReader.readXML("bean_test.xml");

        var registry = xmlReader.getRegistry();
        assertEquals(1, registry.size());
        var beanDefinition = registry.get("User");
        assertEquals("io.talkwithkeyboard.code.bean.User", beanDefinition.getBeanClass().getName());
        assertNull(beanDefinition.getBean());
    }
}
