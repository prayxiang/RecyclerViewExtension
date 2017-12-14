package com.prayxiang.recyclerview.extension;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by prayxiang on 2017/10/17.
 *
 * @description TODO
 */
@SuppressWarnings("all")
public class ItemViewHolder extends ViewHolder {
    public ItemViewHolder(View itemView) {
        super(itemView);
    }


    private SparseArray<View> mViews = new SparseArray<>();
    private Object tag;
    private Object object;
    private Object adapter;


    public void setAdapter(Object adapter) {
        this.adapter = adapter;
    }

    public <T> T getAdapter() {
        return (T) adapter;
    }

    public <T> T getItem() {
        return (T) object;
    }

    public void setItem(Object o) {
        object = o;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Object getTag() {
        return tag;
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }


    public <T extends View> T findViewById(@IdRes int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public ImageView getImageView(@IdRes int resId) {
        return findViewById(resId);
    }

    public TextView getTextView(@IdRes int resId) {
        return findViewById(resId);
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public void setText(@IdRes int resId, String string) {
        TextView textView = findViewById(resId);
        textView.setText(string);
    }

    public void setImageResource(@IdRes int resId, @DrawableRes int imageId) {
        getImageView(resId).setImageResource(imageId);
    }

    public int dp2px(float size) {
        return Math.round(getContext().getResources().getDisplayMetrics().density * size);
    }

    public String getString(int resId) {
        return itemView.getResources().getString(resId);
    }


}
