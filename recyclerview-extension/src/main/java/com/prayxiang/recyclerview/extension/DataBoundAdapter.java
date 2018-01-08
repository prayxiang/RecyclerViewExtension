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

import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_END;
import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_FAIL;
import static com.prayxiang.recyclerview.extension.tools.Status.STATUS_SUCCESS;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class DataBoundAdapter extends MultiTypeAdapter {
    private RecyclerView recyclerView;


    public DataBoundAdapter() {

    }

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



}
