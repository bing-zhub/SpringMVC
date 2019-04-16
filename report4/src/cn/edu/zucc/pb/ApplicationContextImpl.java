package cn.edu.zucc.pb;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApplicationContextImpl implements ApplicationContext {
    /** Ҫ�����������ļ�*/
    private File file;
    /** ���Bean�����ʵ��*/
    private Map map=new HashMap();

    /** ���������ļ�,ʵ��������,������������������*/
    public ApplicationContextImpl(String configfile) throws Exception {
        URL url =this.getClass().getClassLoader().getResource(configfile);
        try {
            file=new File(url.toURI());
            xmlParse(file);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void xmlParse(File file) throws JDOMException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        SAXBuilder builder=new SAXBuilder();
        Document document=builder.build(file);

        // ����XPath����,�����ȡXPath����
        XPathFactory factory = XPathFactory.instance();
        // ��ȡ���е�Bean�ڵ�
        XPathExpression expression = factory.compile("//bean");

        List beans = expression.evaluate(document);
        Iterator iterator=beans.iterator();
        while(iterator.hasNext()){
            Element bean=(Element) iterator.next();

            // ��ȡ�����ļ���id����ֵ
            String id = bean.getAttributeValue("id");
            String cls = bean.getAttributeValue("class");
            // �����õ������Ӧ��Ϣ,�������õ����ʵ������
            Class clazz = Class.forName(cls);
            Object object = clazz.newInstance();
            // ��ȡ������з���,Ȼ��ͨ��set���������������������ֵ
            Method[] methods=clazz.getDeclaredMethods();
            // ����Bean�ڵ��µ��������Ժͷ���,һһƥ��,�������ö��������ֵ
            List<Element> list=bean.getChildren("property");
            for(Element element:list){
                for(int i=0;i<methods.length;i++){
                    String methodName=methods[i].getName();
                    // ������
                    String attrInClass="";
                    // �������set����
                    if(methodName.startsWith("set")){
                        // �����ֻ��ȡset�����ķ���������ת��ΪСд������
                        attrInClass=methodName.substring(3).toLowerCase();
                        // ����Ϊ��ͨ���������
                        if(element.getAttribute("name")!=null){
                            if(attrInClass.equals(element.getAttributeValue("name"))){
                                // �������������ֵ
                                methods[i].invoke(object,element.getAttributeValue("value"));
                            }
                        }else if(element.getAttribute("ref")!=null){
                            // ����Ϊ���ö��������
                            methods[i].invoke(object,map.get(element.getAttributeValue("ref")));
                        }
                    }
                }
            }
            // ��������ӵ���������
            map.put(id,object);
        }
    }
    /** ��ȡBean����*/
    public Object getBean(String name) {
        return map.get(name);
    }
}
