package me.halin.android_architecture.uikit;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于Observer的Adapter，通过实现Observer接口，实现数据绑定
 */
public abstract class BaseDiffAdapter<T> extends DataBindingAdapter<T> implements Observer<List<T>> {


    private final List<T> data = new ArrayList<>();
    private DiffUtilFactory<T> diffUtilFactory;

    public BaseDiffAdapter(DataBindingHolder.Factory<T> holderFactory, DiffUtilFactory<T> diffUtilFactory) {
        super(holderFactory);
        this.diffUtilFactory = diffUtilFactory;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public T getModelAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public void onChanged(@Nullable final List<T> ts) {
        ArrayList<T> oldData = new ArrayList<>(data);
        if (ts == null) {
            // 没有新数据，不显示内容
            data.clear();
            this.notifyItemRangeRemoved(0, oldData.size());
            return;
        }
        DiffUtil.Callback callback = diffUtilFactory.buildDiffUtil(oldData, ts);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback, true);
        data.clear();
        data.addAll(ts);
        result.dispatchUpdatesTo(this);
    }

    public interface DiffUtilFactory<T> {
        DiffUtil.Callback buildDiffUtil(List<T> oldList, List<T> newList);
    }


}
