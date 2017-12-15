package com.prayxiang.recyclerview.extension;


import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.prayxiang.recyclerview.extension.internal.BaseStrategyAdapter;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class BaseAdapter extends MultiTypeAdapter {
    private RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    static final Object DB_PAYLOAD = new Object();
    final OnRebindCallback mOnRebindCallback = new OnRebindCallback() {
        @Override
        public boolean onPreBind(ViewDataBinding binding) {
            if (recyclerView == null || recyclerView.isComputingLayout()) {
                return true;
            }
            int childAdapterPosition = recyclerView.getChildAdapterPosition(binding.getRoot());
            if (childAdapterPosition == RecyclerView.NO_POSITION) {
                return true;
            }
            notifyItemChanged(childAdapterPosition, DB_PAYLOAD);
            return false;
        }
    };

    private BaseStrategyAdapter strategy;

    public BaseAdapter() {
        strategy = new BaseStrategyAdapter();
        setStrategy(strategy);
    }
    public void setLoadListener(LoadListener loadListener) {
        strategy.setLoadListener(loadListener);
    }

    public void setLimit(int limit) {
        strategy.setLimit(limit);
    }

    public boolean isEnableLoadMore() {
        return strategy.isEnableLoadMore();
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        strategy.setEnableLoadMore(enableLoadMore);
    }
    public void addFixedViewBinder(Object o) {
        strategy.addFixedViewBinder(o);
    }


    public void setEnableEmpty(boolean enableEmpty) {
        strategy.setEnableEmpty(enableEmpty);
    }

    public BaseStrategyAdapter getStrategy() {
        return strategy;
    }
}
