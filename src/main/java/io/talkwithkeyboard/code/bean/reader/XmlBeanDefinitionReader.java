package io.talkwithkeyboard.code.bean.reader;


import io.talkwithkeyboard.code.bean.BeanDefinition;
import io.talkwithkeyboard.code.bean.BeanDefinition.PropertyValue;
import io.talkwithkeyboard.code.bean.BeanReference;
import io.talkwithkeyboard.code.bean.resource.ResourceLoader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 通过路径解析xml文件
     */
    public void readXML(String location) throws Exception {
        var resourceLoader = new ResourceLoader();
        var inputStream = resourceLoader.getResource(location).getInputStream();
        var document = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(inputStream);

        parseBeanDefinitions(document.getDocumentElement());
    }

    /**
     * 从xml的root节点开始解析
     */
    private void parseBeanDefinitions(Element root) {
        var childNodes = root.getChildNodes();

        for (var i = 0; i < childNodes.getLength(); i++) {
            var node = childNodes.item(i);
            if (node instanceof Element) {
                var childElement = (Element) node;
                processBeanDefinition(childElement);
            }
        }
    }

    /**
     * 对一个给定的element进行处理
     */
    private void processBeanDefinition(Element element) {
        var name = element.getAttribute("name");
        var className = element.getAttribute("class");
        var beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(className);
        addPropertyValues(element, beanDefinition);
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 为一个beanDefinition添加属性
     */
    private void addPropertyValues(Element element, BeanDefinition beanDefinition) {
        var propertyNode = element.getElementsByTagName("property");

        for (var i = 0; i < propertyNode.getLength(); i++) {
            var node = propertyNode.item(i);
            if (node instanceof Element) {
                var childElement = (Element) node;
                var name = childElement.getAttribute("name");
                var value = childElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.addPropertyValue(new PropertyValue(name, value));
                } else {
                    var ref = childElement.getAttribute("ref");
                    if (ref != null && ref.length() > 0) {
                        var beanRef = new BeanReference(ref);
                        beanDefinition.addPropertyValue(new PropertyValue(name, beanRef));
                    } else {
                        throw new IllegalArgumentException(
                            "Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                }
            }
        }
    }
}
