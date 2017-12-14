package com.prayxiang.recyclerview.extension.tools;

import android.view.View;

/**
 * Created by prayxiang on 2017/10/17.
 */

public interface OnItemClickListener<T> {
    void onItemClick(View view, T item, int position);
}
