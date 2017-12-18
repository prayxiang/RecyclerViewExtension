package com.prayxiang.recyclerview.extension.internal;

import android.support.v7.widget.RecyclerView;

import com.prayxiang.recyclerview.extension.LoadListener;
import com.prayxiang.recyclerview.extension.tools.Empty;
import com.prayxiang.recyclerview.extension.tools.LoadMoreScrollListener;
import com.prayxiang.recyclerview.extension.tools.LoaderMore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class BaseStrategyAdapter extends DefaultStrategyAdapter {
    private LoaderMore loaderMore = new LoaderMore();
    private Empty empty = new Empty();
    private int fixedOffset;
    private int footOffset;
    private int emptyOffset;
    private int limit = 1;
    private boolean enableLoadMore = true;
    private boolean enableEmpty = true;
    private LoadListener loadListener;

    private LoadMoreScrollListener listener = new LoadMoreScrollListener(loaderMore);


    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
        loaderMore.setLoadListener(loadListener);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(listener);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.removeOnScrollListener(listener);
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

    public BaseStrategyAdapter() {
        items = new ArrayList<>();
    }


    public void addFixedViewBinder(Object o) {
        items.add(fixedOffset, o);
        adapter.notifyItemInserted(fixedOffset);
        fixedOffset++;
    }

    public void removeFixedViewBinder(Object o) {
        int index = items.indexOf(o);
        if (index > 0) {
            items.remove(index);
            fixedOffset--;
            adapter.notifyItemRemoved(index);
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
        adapter.notifyItemRangeInserted(items.size() - footOffset, collection.size());
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



}
