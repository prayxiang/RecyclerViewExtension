package com.prayxiang.recyclerview.extension.tools;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.prayxiang.recyclerview.extension.ItemViewBinder;
import com.prayxiang.recyclerview.extension.ItemViewHolder;
import com.prayxiang.recyclerview.extension.R;

/**
 * Created by xianggaofeng on 2017/12/13.
 */

public class EmptyViewBinder extends ItemViewBinder<Empty, ItemViewHolder> {
    public EmptyViewBinder() {

    }

    @Override
    public ItemViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        ItemViewHolder holder = new ItemViewHolder(inflater.inflate(R.layout.recycler_extension_default_empty, parent, false));
        RecyclerView recyclerView = (RecyclerView) parent;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.d("xgf5", newState + "");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("xgf6", dy + "");
            }
        });

        Log.d("xgf5", "onCreateViewHolder: " );
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, Empty item) {
        super.onBindViewHolder(holder, item);
        RecyclerView recyclerView = (RecyclerView) holder.itemView.getParent();

        Log.d("xgf5", "onBindViewHolder: " + holder.getItem());
        int height = 0;
//        for (int i = 0, count = recyclerView.getChildCount(); i < count; i++) {
//            height += recyclerView.getChildAt(i).getHeight();
//        }
//        if (height < recyclerView.getHeight()) {
//            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, holder.itemView.getHeight() + recyclerView.getHeight() - height));
//        }
    }


    @Override
    public void onViewDetachedFromWindow(ItemViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);

        final RecyclerView recyclerView = (RecyclerView) viewHolder.itemView.getParent();
        Log.d("xgf5", "onViewDetachedFromWindow: " + viewHolder.getItem());
    }

    @Override
    public void onViewAttachedToWindow(ItemViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        final RecyclerView recyclerView = (RecyclerView) viewHolder.itemView.getParent();

        Log.d("xgf5", "onViewAttachedToWindow: " + viewHolder.getItem());

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        int extent = 0;
        if (manager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) manager).getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                extent = recyclerView.computeVerticalScrollExtent();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (extent + viewHolder.itemView.getMinimumHeight() < recyclerView.getHeight()) {
                        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                        params.height = recyclerView.getHeight() - extent;
//                        viewHolder.itemView.setMinimumHeight( recyclerView.getHeight() - extent);
//                        viewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, recyclerView.getHeight() - extent));
                    }else {
                        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                        params.height = -2;
                    }
                }
            } else {
                extent = recyclerView.computeHorizontalScrollOffset();
            }
        }

//
//        Log.d("xgf5", "onViewAttachedToWindow: " + recyclerView.computeVerticalScrollExtent() + " height" + recyclerView.getHeight());
//        recyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("xgf5", "onViewAttachedToWindow: " + recyclerView.computeVerticalScrollExtent() + " height" + recyclerView.getHeight());
//            }
//        }, 3000);
    }
}
