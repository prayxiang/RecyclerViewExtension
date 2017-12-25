package com.prayxiang.recyclerview.extension.tools;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.prayxiang.recyclerview.extension.BR;
import com.prayxiang.recyclerview.extension.LoadListener;

import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_DEFAULT;
import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_END;
import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_LOADING;
import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_SUCCESS;


/**
 * Created by xianggaofeng on 2017/6/6.
 */
public class LoaderMore extends BaseObservable {

    @Bindable
    public int loadMoreStatus = STATUS_DEFAULT;
    public boolean loadMoreEndGone = false;
    public int currentPage = 1;
    private boolean active = true;


    public LoaderMore() {

    }

    public void setIncludeFirst(boolean includeFirst) {
        isIncludeFirst = includeFirst;
        currentPage = includeFirst ? 0 : 1;
    }

    public LoaderMore(boolean includeFirst) {
        currentPage = includeFirst ? 0 : 1;
    }


    public int getLoadMoreStatus() {
        return loadMoreStatus;
    }

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.loadMoreStatus = loadMoreStatus;
        if (loadMoreStatus == STATUS_SUCCESS) {
            currentPage++;
        }

        notifyPropertyChanged(BR.loadMoreStatus);
    }

    public boolean isIncludeFirst;

    public void reset() {
        setLoadMoreStatus(STATUS_DEFAULT);
        if (isIncludeFirst) {
            currentPage = 0;
        } else {
            currentPage = 1;
        }
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private LoadListener loadListener;

    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }
    public void onClick() {
        if (loadMoreStatus != STATUS_END && loadMoreStatus != STATUS_LOADING && isActive()) {
            if (loadListener != null) {
                setLoadMoreStatus(STATUS_LOADING);
                loadListener.load(currentPage);
            }
        }
    }
}
