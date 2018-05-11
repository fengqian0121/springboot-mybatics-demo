package com.example.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqian
 * date 2018/5/9 14:13
 */
public class ClassUtil {

    /**
     * 根据类获取类中所有属性，并返回集合
     * */
    public static List<String> getAllAttributes(Object object){
        List<String> attrs=new ArrayList<>();
       try{
           Class clazz=object.getClass();
           Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性
           for(Field f : fields) {
               attrs.add(f.getName());
           }
       }catch(Exception e){
           e.printStackTrace();
       }
        return attrs;
    }


    /**
     * 根据属性，获取get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     */
    public static Object getGetMethod(Object ob , String name){
        Method[] m = ob.getClass().getMethods();
        try {
            for(int i = 0;i < m.length;i++){
                if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                    return m[i].invoke(ob);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
