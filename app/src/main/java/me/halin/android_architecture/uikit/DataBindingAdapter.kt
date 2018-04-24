package me.halin.android_architecture.uikit

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

public abstract class DataBindingAdapter<T>(val holderFactory: DataBindingHolder.HolderFactory<T>) : RecyclerView.Adapter<DataBindingHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingHolder {
        return holderFactory.createHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: DataBindingHolder, position: Int) {
        holder.bindTo(getModelAtPosition(position))
    }

    override fun getItemViewType(position: Int): Int {
        return holderFactory.getViewType(getModelAtPosition(position))
    }


    public abstract fun getModelAtPosition(position: Int): T
}