package com.wordUtil.util;
import org.apache.commons.beanutils.BeanMap;
import java.util.*;

public class BeanUtils {
    /**
     * 将Bean对象转换成Map对象
     */
    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }

        if( obj instanceof Map) {
            map.putAll((Map)obj);
            return map;
        }
        BeanMap beanMap = new BeanMap(obj);
        Iterator<String> it = beanMap.keyIterator();
        while (it.hasNext()) {
            String name = it.next();
            Object value = beanMap.get(name);
            // 转换时会将类名也转换成属性，此处去掉
            if (value != null && !name.equals("class")) {
                map.put(name, value);
            }
        }
        return map;
    }
}