package com.prayxiang.recyclerview.extension;

import com.prayxiang.recyclerview.extension.tools.Empty;
import com.prayxiang.recyclerview.extension.tools.LoaderMore;

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
    private ViewBinder emptyViewBinder;
    private ViewBinder loadMoreViewBinder;

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

    public TypeBuilder<T> addFixViewBinder(int type, ViewBinder viewBinder) {
        map.put(type, viewBinder);
        return this;
    }

    public TypeBuilder<T> addEmptyViewBinder(ViewBinder viewBinder) {
        map.put(Empty.class.hashCode(), viewBinder);

        return this;
    }

    public TypeBuilder<T> addLoadMoreViewBinder(ViewBinder viewBinder) {
        map.put(LoaderMore.class.hashCode(), viewBinder);
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
                    return item.getClass().hashCode();
                }
            };
        }
        adapter.mTypeStrategy = strategy;
        adapter.mPool.registerAll(map);
        return adapter;
    }

    public MultiTypeAdapter build() {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        if (strategy == null) {
            strategy = new TypeStrategy<T>() {
                @Override
                public int getItemViewType(Object item) {
                    return item.getClass().hashCode();
                }
            };
        }
        adapter.mTypeStrategy = strategy;
        adapter.mPool.registerAll(map);
        return adapter;
    }
    public MultiTypeAdapter build(Class<? extends MultiTypeAdapter> cls) {
        MultiTypeAdapter adapter;
        if(cls.isAssignableFrom(MultiTypeAdapter.class)){
            try {
                adapter = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new RuntimeException("unSupport adapter");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("unSupport adapter");
            }
        }else {
            throw new RuntimeException("unSupport adapter");
        }
        if (strategy == null) {
            strategy = new TypeStrategy<T>() {
                @Override
                public int getItemViewType(Object item) {
                    return item.getClass().hashCode();
                }
            };
        }
        adapter.mTypeStrategy = strategy;
        adapter.mPool.registerAll(map);
        return adapter;
    }



}
