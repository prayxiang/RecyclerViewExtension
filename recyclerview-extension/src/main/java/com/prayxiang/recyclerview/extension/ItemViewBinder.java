package com.prayxiang.recyclerview.extension;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by prayxiang on 2017/9/7.
 */

public class ItemViewBinder<T, V extends ItemViewHolder> extends ViewBinder<T, V> {
    public V onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    final public void onBindViewHolder(V holder, List<Object> payloads) {
        super.onBindViewHolder(holder, payloads);
        onBindViewHolder(holder, (T) holder.getItem());
    }

    public void onBindViewHolder(V holder, T t) {

    }
}
