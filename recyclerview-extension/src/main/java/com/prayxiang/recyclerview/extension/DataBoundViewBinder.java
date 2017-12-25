package com.prayxiang.recyclerview.extension;


import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import static com.prayxiang.recyclerview.extension.BaseAdapter.DB_PAYLOAD;

/**
 * Created by prayxiang on 2017/10/17.
 *
 * @description TODO
 */

public class DataBoundViewBinder<T, V extends ViewDataBinding> extends ViewBinder<T, DataBoundViewHolder<V>> {
    private int mLayoutId;
    private android.databinding.DataBindingComponent component;

    public DataBoundViewBinder(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
    }

    public DataBoundViewBinder(@LayoutRes int layoutId, android.databinding.DataBindingComponent component) {
        this.mLayoutId = layoutId;
        this.component = component;
    }

    @Override
    final public DataBoundViewHolder<V> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        DataBoundViewHolder<V> vh = DataBoundViewHolder.create(parent, mLayoutId, component);
        onDataBoundCreated(vh);
        DataBoundAdapter adapter = (DataBoundAdapter) this.adapter;
        vh.binding.addOnRebindCallback(adapter.mOnRebindCallback);
        return vh;
    }

    public void onDataBoundCreated(DataBoundViewHolder<V> vh) {
    }

    public void bindItem(DataBoundViewHolder<V> holder, T item) {
    }

    @Override
    final public void onBindViewHolder(DataBoundViewHolder<V> holder, List<Object> payloads) {
        if (payloads.isEmpty() || hasNonDataBindingInvalidate(payloads)) {
            bindItem(holder, (T) holder.getItem());
        }
        holder.binding.executePendingBindings();
    }


    private boolean hasNonDataBindingInvalidate(List<Object> payloads) {
        for (Object payload : payloads) {
            if (payload != DB_PAYLOAD) {
                return true;
            }
        }
        return false;
    }
}
