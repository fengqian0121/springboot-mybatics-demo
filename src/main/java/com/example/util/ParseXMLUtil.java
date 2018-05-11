package com.example.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author fengqian
 * date 2018/4/23 13:41
 */
public class ParseXMLUtil {
    //招生省份计划为键，列对应为值的map集合
    public static Map<String, Map<String, String>> plansMap = new HashMap<>();
    private static final String XML_FILE_PATH="config/columnMapping.xml";

    /**
     * 读取xml配置文件
     */
    public static void planList() {
        //获取存放映射关系的xml
        URL url = ClassUtils.getDefaultClassLoader().getResource(XML_FILE_PATH);
        try {
            File file = new File(url.getPath());
            SAXReader reader = new SAXReader();
            //读取xml文件到Document中
            Document doc = reader.read(file);
            //获取xml文件的根节点
            Element rootElement = doc.getRootElement();
            //定义一个Element用于遍历
            Element fooElement = null;
            Element colElement = null;
            Attribute planId = null;
            Attribute colExcelId = null;
            Attribute planValue = null;
            //遍历所有名叫“planType”的节点
            for (Iterator planType = rootElement.elementIterator("planType"); planType.hasNext(); ) {
                fooElement = (Element) planType.next();
                //获取id属性值
                planId = fooElement.attribute("id");
                //获取value属性值
                planValue = fooElement.attribute("value");
                System.out.println(planId.getValue() + " : " + planValue.getValue());

                //遍历colExcel子节点
                Map<String, String> map = new HashMap<>();
                for (Iterator colExcel = fooElement.elementIterator("colExcel"); colExcel.hasNext(); ) {
                    colElement = (Element) colExcel.next();
                    colExcelId = colElement.attribute("id");
                    map.put(colExcelId.getValue(), colElement.element("colDb").getText());
                }
                plansMap.put(planId.getValue(), map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        planList();
        System.out.println(plansMap);
    }
}
