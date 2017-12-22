package com.prayxiang.recyclerview.extension;


import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;

import android.support.v7.widget.RecyclerView;


import com.prayxiang.recyclerview.extension.tools.Empty;
import com.prayxiang.recyclerview.extension.tools.LoadMoreScrollListener;
import com.prayxiang.recyclerview.extension.tools.LoaderMore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class BaseAdapter extends MultiTypeAdapter {
    private RecyclerView recyclerView;

    private LoaderMore loaderMore = new LoaderMore();
    private LoadMoreScrollListener listener = new LoadMoreScrollListener(loaderMore);

    private Empty empty = new Empty();
    private int fixedOffset;
    private int footOffset;
    private int limit = 1;
    private boolean enableLoadMore = true;
    private boolean enableEmpty = true;


    public BaseAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(listener);

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.removeOnScrollListener(listener);
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


    public void setLoadListener(LoadListener loadListener) {
        loaderMore.setLoadListener(loadListener);
    }


    public boolean isEnableLoadMore() {
        return enableLoadMore;
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
    }


    public void loadFail() {
        loaderMore.setLoadMoreStatus(LoaderMore.STATUS_FAIL);
    }


    public void addFixedViewBinder(Object o) {
        items.add(fixedOffset, o);
        notifyItemInserted(fixedOffset);
        fixedOffset++;
    }

    public void removeFixedViewBinder(Object o) {
        int index = items.indexOf(o);
        if (index > 0) {
            items.remove(index);
            fixedOffset--;
            notifyItemRemoved(index);
        }
    }


    public void replace(Collection<?> collection) {
        loaderMore.reset();
        clear();
        footOffset = 0;
        if (collection.size() == 0) {
            if (enableEmpty) {
                items.add(empty);
                return;
            }
        }
        items.addAll(collection);
        if (getDataSize() >= limit && enableLoadMore) {
            loaderMore.setActive(true);
            items.add(loaderMore);
            footOffset++;
        }
    }


    public void setEnableEmpty(boolean enableEmpty) {
        this.enableEmpty = enableEmpty;
    }

    @Override
    public void insert(Collection<?> collection) {
        if (collection == null) {
            collection = Collections.emptyList();
            loaderMore.setLoadMoreStatus(LoaderMore.STATUS_FAIL);
        } else if (collection.size() < limit) {
            loaderMore.setLoadMoreStatus(LoaderMore.STATUS_END);
        } else {
            loaderMore.setLoadMoreStatus(LoaderMore.STATUS_SUCCESS);
        }
        items.addAll(items.size() - footOffset, collection);
        notifyItemRangeInserted(items.size() - footOffset, collection.size());
    }

    @Override
    public void insert(int position, Collection<?> collection) {
        items.addAll(position + fixedOffset, collection);
    }

    @Override
    public void display(Collection<?> collection) {
        if (getDataSize() == 0) {
            replace(collection);
        } else {
            insert(collection);
        }
    }

    public int getDataSize() {
        return items.size() - fixedOffset - footOffset;
    }


    public void clear() {
        for (int i = items.size() - footOffset - 1; i >= fixedOffset; i--) {
            items.remove(i);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getLastItem() {
        if (items.size() == 0) {
            return null;
        }
        return (T) items.get(items.size() - 1 - footOffset);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LoaderMore getLoaderMore() {
        return loaderMore;
    }

    public Empty getEmpty() {
        return empty;
    }
}
