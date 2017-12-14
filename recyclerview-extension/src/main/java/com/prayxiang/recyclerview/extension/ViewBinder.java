package com.prayxiang.recyclerview.extension;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by prayxiang on 2017/10/17.
 *
 * @description TODO
 */
@SuppressWarnings("all")
public class ViewBinder<T, V extends ViewHolder> {

    MultiTypeAdapter adapter;

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public V onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

   public void onBindViewHolder(V holder, List<Object> payloads) {

    }




    public void onViewDetachedFromWindow(V viewHolder) {

    }

    public void onViewAttachedToWindow(V viewHolder) {
    }
}
