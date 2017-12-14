package com.prayxiang.recyclerview.extension.tools;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.prayxiang.recyclerview.extension.BR;

/**
 * Created by xianggaofeng on 2017/12/14.
 */

public class CheckBean extends BaseObservable {
    private Object object;

    private boolean checked;
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CheckBean(Object object, String title) {
        this.object = object;
        this.title = String.valueOf(title);
    }

    public CheckBean(Object object) {
        this.object = object;
        this.title = String.valueOf(object);
    }

    public <T> T getObject() {
        return (T) object;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        notifyPropertyChanged(BR.checked);
    }

    @Bindable
    public boolean isChecked() {
        return checked;
    }
}
