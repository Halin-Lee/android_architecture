package me.halin.android_architecture.uikit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 抽离模型与UI,所有的UI操作封装到Factory中实现
 */
public abstract class DataBindingAdapter<T> extends RecyclerView.Adapter<DataBindingHolder> {


    public final DataBindingHolder.Factory<T> holderFactory;

    public DataBindingAdapter(DataBindingHolder.Factory<T> holderFactory) {
        this.holderFactory = holderFactory;
    }

    @NonNull
    @Override
    public DataBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return holderFactory.createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataBindingHolder holder, int position) {
        holder.bindTo(getModelAtPosition(position));
    }


    @Override
    public int getItemViewType(int position) {
        T model = getModelAtPosition(position);
        return this.holderFactory.getViewType(model);
    }

    public abstract T getModelAtPosition(int position);


}