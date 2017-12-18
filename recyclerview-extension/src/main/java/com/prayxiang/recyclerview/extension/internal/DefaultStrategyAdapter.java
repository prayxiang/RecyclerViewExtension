package com.prayxiang.recyclerview.extension.internal;

import android.support.v7.widget.RecyclerView;


import com.prayxiang.recyclerview.extension.MultiTypeAdapter;
import com.prayxiang.recyclerview.extension.StrategyAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class DefaultStrategyAdapter implements StrategyAdapter {
    protected MultiTypeAdapter adapter;
    protected List<Object> items = Collections.emptyList();
    protected RecyclerView recyclerView;

    public void setItems(List<?> items) {
        this.items = (List<Object>) items;
    }

    public List<?> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public void replace(Collection<?> collection) {
        if (collection != null) {
            items = (List<Object>) collection;
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void insert(Collection<?> collection) {
        throw new RuntimeException("unSupport operation");
    }

    @Override
    public void insert(int position, Collection<?> collection) {
        throw new RuntimeException("unSupport operation");
    }

    @Override
    public void display(Collection<?> collection) {
        replace(collection);
    }

    @Override
    public void onAttachAdapter(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onDetachAdapter(MultiTypeAdapter adapter) {
        this.adapter = null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        recyclerView = null;
    }

}
