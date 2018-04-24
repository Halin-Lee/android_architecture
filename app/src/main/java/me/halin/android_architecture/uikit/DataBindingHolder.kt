package me.halin.android_architecture.uikit

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.halin.android_architecture.BR

public class DataBindingHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {


    public fun bindTo(obj: Any?) {
        binding.setVariable(BR.viewModel, obj)
        binding.executePendingBindings()
    }


    public interface HolderFactory<T> {
        fun createHolder(parent: ViewGroup, type: Int): DataBindingHolder
        fun getViewType(model: T): Int
    }

    public abstract class BaseFactory<T>(private val inflater: LayoutInflater) : HolderFactory<T> {
        override fun createHolder(parent: ViewGroup, type: Int): DataBindingHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, type, parent, false)
            return DataBindingHolder(binding)
        }
    }
}