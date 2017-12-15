package com.prayxiang.recyclerview.extension.tools;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prayxiang.recyclerview.extension.ItemViewBinder;
import com.prayxiang.recyclerview.extension.ItemViewHolder;


public class DividerViewBinder extends ItemViewBinder<Divider, ItemViewHolder> {

    private int color;
    private Rect rect;

    public DividerViewBinder() {

    }

    public DividerViewBinder(int color, Rect rect) {
        this.color = color;
        this.rect = rect;
    }

    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        float density = parent.getContext().getResources().getDisplayMetrics().density;
        View view = new View(parent.getContext());
        view.setMinimumHeight(Math.round(1 * density));
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(1, 1, parent.getContext().getResources().getDisplayMetrics()));
        view.setLayoutParams(params);

        if (rect != null) {
            params.setMargins(Math.round(density * rect.left), Math.round(density * rect.top), Math.round(density * rect.right), Math.round(density * rect.bottom));
        }
        if (color != 0) {
            view.setBackgroundColor(color);
        }

        return new ItemViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ItemViewHolder holder, @NonNull Divider item) {
    }
}
