package io.talkwithkeyboard.code.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.talkwithkeyboard.code.bean.reader.XmlBeanDefinitionReader;
import io.talkwithkeyboard.code.bean.resource.ResourceLoader;
import org.junit.jupiter.api.Test;

public class AutowireBeanFactoryTest {

    @Test
    void testGetBean() throws Exception {
        var resourceLoader = new ResourceLoader();
        var xmlReader = new XmlBeanDefinitionReader(resourceLoader);
        xmlReader.readXML("bean_test.xml");

        var factory = new AutowireBeanFactory();
        factory.registerBeanDefinition("User", xmlReader.getRegistry().get("User"));
        var bean = factory.getBean("User");

        assertNotNull(bean);
        assertTrue(bean instanceof User);

        var user = (User) bean;

        assertEquals("songwei", user.getName());
        assertEquals("25", user.getAge());
    }
}
