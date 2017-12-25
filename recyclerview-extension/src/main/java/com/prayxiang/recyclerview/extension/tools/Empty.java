package com.prayxiang.recyclerview.extension.tools;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.prayxiang.recyclerview.extension.BR;
import com.prayxiang.recyclerview.extension.LoadListener;

import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_EMPTY;
import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_LOADING;

/**
 * Created by xianggaofeng on 2017/12/13.
 */

public class Empty extends BaseObservable {
    public boolean mFillViewPort;

    private boolean active = true;

    private int status = STATUS_EMPTY;

    @Bindable
    public int getStatus() {
        return status;
    }

    public boolean isFillViewPort() {
        return mFillViewPort;
    }

    public void setFillViewPort(boolean fillViewPort) {
        this.mFillViewPort = fillViewPort;
    }

    public void onClick() {
        if (status != STATUS_LOADING &&status!=STATUS_EMPTY&& isActive()) {
//            if (loadListener != null) {
//                setLoadMoreStatus(LoaderMore.STATUS_LOADING);
//                loadListener.load(currentPage);
//            }
        }
    }
    private LoadListener loadListener;

    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void setLoadStatus(int status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
