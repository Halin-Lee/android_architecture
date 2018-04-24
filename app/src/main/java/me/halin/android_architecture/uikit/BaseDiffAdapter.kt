package me.halin.android_architecture.uikit

import android.arch.lifecycle.Observer
import android.support.v7.util.DiffUtil

public abstract class BaseDiffAdapter<T>(holderFactory: DataBindingHolder.HolderFactory<T>, val diffUtilFactory: DiffUtilFactory<T>) :
        DataBindingAdapter<T>(holderFactory),
        Observer<List<T>> {


    private val data = ArrayList<T>()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getModelAtPosition(position: Int): T {
        return data[position]
    }

    override fun onChanged(t: List<T>?) {
        val oldData = ArrayList<T>(data)
        if (t == null) {
            // 没有新数据
            data.clear()
            this.notifyItemRangeRemoved(0, oldData.size)
            return
        }

        val callback = diffUtilFactory.buildDiffUtil(oldData, t)
        val result = DiffUtil.calculateDiff(callback, true)
        data.clear()
        data.addAll(t)
        result.dispatchUpdatesTo(this)
    }


    public interface DiffUtilFactory<T> {
        fun buildDiffUtil(oldList: List<T>, newList: List<T>):DiffUtil.Callback
    }
}