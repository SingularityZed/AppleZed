package com.zed.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: bean copy
 *
 * @author zed
 */
@SuppressWarnings("unchecked")
public class AutoMapperUtil {

    /**
     * 实体转换
     *
     * @param source
     * @param destination
     * @param <TSource>
     * @param <TDestination>
     * @return
     */
    public static <TSource, TDestination> TDestination mapping(TSource source, TDestination destination) {
        Method[] srcMethods = source.getClass().getMethods();
        Method[] destMethods = destination.getClass().getMethods();
        for (Method m : srcMethods) {
            String srcMethodName = m.getName();
            // 调用get方法
            if (srcMethodName.startsWith("get")) {
                try {
                    // 获取当前方法返回值(获取当前属性值)
                    Object getValue = m.invoke(source);
                    for (Method dm : destMethods) {
                        // 目标方法名称
                        String destMethodName = dm.getName();
                        if (destMethodName.startsWith("set") && destMethodName.substring(3).equals(srcMethodName.substring(3))) {
                            dm.invoke(destination, getValue);
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return destination;
    }

    /**
     * List转换
     *
     * @param src
     * @param target
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> mappingList(List<S> src, List<T> target, Class<?> targetClass) {
        for (S s : src) {
            try {
                Object object = targetClass.newInstance();
                target.add((T) object);
                mapping(s, object);
            } catch (Exception ignored) {
            }
        }
        return target;
    }

    /**
     * page转换
     *
     * @param src
     * @param targetClass
     * @param <targetClass>
     * @return
     */
    public static <targetClass> Page mappingPage(Page src, Class<?> targetClass) {
        Page<targetClass> target = new Page<>();
        mapping(src, target);
        List<targetClass> list = new ArrayList<>();
        mappingList(src.getRecords(), list, targetClass);
        target.setRecords(list);
        return target;
    }

    /**
     * POJO转换输出POJO
     * 使用BeanUtils工具
     *
     * @param t
     * @param kcz
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> K toPOJO(T t, Class<K> kcz) {
        K k = BeanUtils.instantiateClass(kcz);
        BeanUtils.copyProperties(t, k);
        return k;
    }

}
