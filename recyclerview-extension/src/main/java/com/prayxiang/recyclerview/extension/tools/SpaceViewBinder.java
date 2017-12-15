package com.prayxiang.recyclerview.extension.tools;

import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.prayxiang.recyclerview.extension.ItemViewBinder;
import com.prayxiang.recyclerview.extension.ItemViewHolder;


public class SpaceViewBinder extends ItemViewBinder<Space, ItemViewHolder> {
    private int spaceHeight = 10;

    public SpaceViewBinder() {
    }

    public SpaceViewBinder(int spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        android.support.v4.widget.Space space = new android.support.v4.widget.Space(parent.getContext());
        space.setMinimumHeight((int) TypedValue.applyDimension(1, this.spaceHeight, parent.getContext().getResources().getDisplayMetrics()));
        return new ItemViewHolder(space);
    }

    public void onBindViewHolder(@NonNull ItemViewHolder holder, @NonNull Space item) {
        if (item.space != spaceHeight) {
            android.support.v4.widget.Space space = (android.support.v4.widget.Space) holder.itemView;
            space.setMinimumHeight(holder.dp2px(item.space));
        }

    }
}
