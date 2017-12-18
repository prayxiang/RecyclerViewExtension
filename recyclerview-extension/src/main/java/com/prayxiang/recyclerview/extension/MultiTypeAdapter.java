/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prayxiang.recyclerview.extension;


import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.prayxiang.recyclerview.extension.internal.DefaultStrategyAdapter;
import com.prayxiang.recyclerview.extension.internal.MultiTypePool;
import com.prayxiang.recyclerview.extension.tools.OnItemClickListener;

import java.util.Collection;
import java.util.List;


public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> implements StrategyAdapter {
    private OnItemClickListener onItemOnClickListener;

    public void setOnItemOnClickListener(OnItemClickListener<?> onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }

    public void onItemClick(View v, Object item, int position) {
        if (onItemOnClickListener != null) {
            onItemOnClickListener.onItemClick(v, item, position);
        }
    }


    MultiTypePool mPool = new MultiTypePool();
    private DefaultStrategyAdapter mStrategyAdapter;
    TypeStrategy mTypeStrategy;

    public MultiTypeAdapter() {
        setStrategy(new DefaultStrategyAdapter());
    }

    public void setStrategy(DefaultStrategyAdapter strategyAdapter) {
        if (mStrategyAdapter != null) {
            mStrategyAdapter.onDetachAdapter(this);
        }
        this.mStrategyAdapter = strategyAdapter;
        strategyAdapter.onAttachAdapter(this);
    }

    private LayoutInflater inflater;

    public List<?> getItems() {
        return mStrategyAdapter.getItems();
    }

    public void setItems(List<?> items) {
        mStrategyAdapter.setItems(items);
    }

    @Override
    @CallSuper
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewBinder binder = mPool.findViewBinder(viewType);
        binder.adapter = this;
        assert inflater != null;
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int position) {
        throw new IllegalArgumentException("just overridden to make final.");
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        ViewBinder binder = mPool.findViewBinder(holder.getItemViewType());
        holder.setItem(getItem(position));
        binder.onBindViewHolder(holder, payloads);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mStrategyAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewBinder binder = (ViewBinder) mPool.findViewBinder(holder.getItemViewType());
        binder.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ViewBinder binder = (ViewBinder) mPool.findViewBinder(holder.getItemViewType());
        binder.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mStrategyAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        return mTypeStrategy.getItemViewType(position);
    }


    public int getItemCount() {
        return mStrategyAdapter.getItemCount();
    }


    public Object getItem(int position) {
        return mStrategyAdapter.getItem(position);
    }


    public void replace(Collection<?> collection) {
        mStrategyAdapter.replace(collection);
    }

    public void display(Collection<?> items) {
        mStrategyAdapter.display(items);
    }

    public void onAttachAdapter(MultiTypeAdapter adapter) {
        mStrategyAdapter.onAttachAdapter(this);
    }

    public void onDetachAdapter(MultiTypeAdapter adapter) {
        mStrategyAdapter.onDetachAdapter(this);
    }


    public void insert(Collection<?> items) {
        mStrategyAdapter.insert(items);
    }

    @Override
    public void insert(int position, Collection<?> collection) {
        mStrategyAdapter.insert(position, collection);
    }

    //    public MultiTypeAdapter(Object... items) {
//        mStrategyAdapter.display(Arrays.asList(items));
//    }
//
    public void register(Class t, ViewBinder binder) {
        mPool.register(t.hashCode(), binder);
    }
//
//    public void register(int type, ViewBinder binder) {
//        mPool.register(type, binder);
//    }
//
//    public void register(ViewBinder binder) {
//        Type types[] = getGenericParametersType(binder.getClass());
//        mPool.register(types[0].hashCode(), binder);
//    }
//
//
//    public static MultiTypeAdapter create() {
//        return new MultiTypeAdapter();
//    }
//
//    public MultiTypeAdapter addViewBinder(int type, ViewBinder<?, ?> viewBinder) {
//        mPool.register(type, viewBinder);
//        return this;
//    }
//
//    public MultiTypeAdapter addViewBinder(Class clz, ViewBinder<?, ?> viewBinder) {
//        mPool.register(clz.hashCode(), viewBinder);
//        return this;
//    }
//
//    public MultiTypeAdapter addViewBinder(ViewBinder<?, ?> viewBinder) {
//        Type types[] = getGenericParametersType(viewBinder.getClass());
//        mPool.register(types[0].hashCode(), viewBinder);
//        return this;
//    }
//
//    public static Type[] getGenericParametersType(Class clz) {
//        ParameterizedType paramType = (ParameterizedType) clz.getGenericSuperclass();
//        return paramType.getActualTypeArguments();
//    }
//
//    public Object getItem(int position) {
//        return mStrategyAdapter.getItem(position);
//    }
//
//
//    public void setItems(Collection<?> items) {
//        if (items == null) {
//            return;
//        }
//        mStrategyAdapter.setItems((List<?>) items);
//    }

//
//    public void removed(int position) {
//        mStrategyAdapter.remove(position);
//    }
//
//
//    public List<?> getItems() {
//        return mStrategyAdapter.getItems();
//    }

}
