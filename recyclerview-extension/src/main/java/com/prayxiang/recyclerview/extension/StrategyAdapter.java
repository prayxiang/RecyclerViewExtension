package com.prayxiang.recyclerview.extension;

import android.support.v7.widget.RecyclerView;

import java.util.Collection;


public interface StrategyAdapter {
    int getItemCount();

    Object getItem(int position);

    void replace(Collection<?> collection);

    void insert(Collection<?> collection);
    void insert(int position,Collection<?> collection);

    void display(Collection<?> collection);

    void onAttachAdapter(MultiTypeAdapter adapter);

    void onDetachAdapter(MultiTypeAdapter adapter);

    void onAttachedToRecyclerView(RecyclerView recyclerView);

    void onDetachedFromRecyclerView(RecyclerView recyclerView);
}
