package com.mst.csproductservice.domain.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Molin
 * @date 2021/12/28  13:48
 * class description: 通用类型转换工具类
 */
public class CastUtil {

    private CastUtil() {
    }

    public static <T> List<T> castList(Object object, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (object instanceof List<?>) {
            for (Object obj : (List<?>)object) {
                list.add(clazz.cast(obj));
            }
        }
        return list;
    }
}
