package org.smart.framework.helper;

import org.smart.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: zhaoshiping
 * @Date: Created in 13:56 2019/11/11
 * @Description:
 * @Version:
 */
public final class BeanHelper {
    /**
     * 定义bean映射（用于存放bean类和bean实例的映射关系
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object o = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, o);
        }
    }

    /**
     * 获取bean映射
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get Bean by class:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }
}
