package com.prayxiang.recyclerview.extension;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xianggaofeng on 2017/12/15.
 */

public class TypeBuilder<T> {
    private Map<Integer, ViewBinder<?, ? extends ViewHolder>> map = new HashMap<>();

    private TypeStrategy<T> strategy;

    public static <T> TypeBuilder<T> create() {
        return new TypeBuilder<>();
    }

    public TypeBuilder<T> setStrategy(TypeStrategy<T> strategy) {
        this.strategy = strategy;
        return this;
    }

    public TypeBuilder<T> addViewBinder(int type, ViewBinder v) {
        map.put(type, v);
        return this;
    }

    public TypeBuilder<T> addViewBinder(Class cls, ViewBinder v) {
        map.put(cls.hashCode(), v);
        return this;
    }

    public TypeBuilder<T> addViewBinder(ViewBinder viewBinder) {
        Type types[] = getGenericParametersType(viewBinder.getClass());
        map.put(types[0].hashCode(), viewBinder);
        return this;
    }

    public static Type[] getGenericParametersType(Class clz) {
        ParameterizedType paramType = (ParameterizedType) clz.getGenericSuperclass();
        return paramType.getActualTypeArguments();
    }

    public MultiTypeAdapter bindAdpter(MultiTypeAdapter adapter) {
        if (strategy == null) {
            strategy = new TypeStrategy<T>() {
                @Override
                public int getItemViewType(Object item) {
                    return getClass().hashCode();
                }
            };
        }
        adapter.mTypeStrategy = strategy;
        adapter.mPool.registerAll(map);
        return adapter;
    }
//


}
