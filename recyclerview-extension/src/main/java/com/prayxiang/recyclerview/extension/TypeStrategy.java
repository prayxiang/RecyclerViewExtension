package com.prayxiang.recyclerview.extension;

/**
 * Created by xianggaofeng on 2017/12/15.
 */

public interface TypeStrategy<T> {
    int getItemViewType(T item);
}
