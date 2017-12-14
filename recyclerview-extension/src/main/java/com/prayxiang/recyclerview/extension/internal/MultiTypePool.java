package com.prayxiang.recyclerview.extension.internal;

import android.util.SparseArray;

import com.prayxiang.recyclerview.extension.ViewBinder;
import com.prayxiang.recyclerview.extension.ViewHolder;


public class MultiTypePool {
    private final SparseArray<ViewBinder<?, ? extends ViewHolder>> mCaches = new SparseArray<>();

    public ViewBinder<?, ? extends ViewHolder> findViewBinder(Integer integer) {
        return mCaches.get(integer);
    }

    public void register(int integer, ViewBinder<?, ? extends ViewHolder> binder) {
        mCaches.put(integer, binder);
    }
}
