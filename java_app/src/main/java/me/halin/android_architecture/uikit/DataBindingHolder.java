package me.halin.android_architecture.uikit;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.halin.android_architecture.BR;

public class DataBindingHolder extends RecyclerView.ViewHolder {
    protected ViewDataBinding mBinding;

    public DataBindingHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }

    public void bindTo(Object obj) {
        mBinding.setVariable(BR.viewModel, obj);
        mBinding.executePendingBindings();
    }


    /**
     * Adapter UI工厂类，抽象UI操作
     */
    public interface Factory<T> {
        DataBindingHolder createHolder(ViewGroup parent, int type);

        int getViewType(T model);
    }

    /**
     * Adapter UI工厂类的默认实现
     */
    public static abstract class BaseFactory implements Factory {
        private final LayoutInflater inflater;

        public BaseFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        public DataBindingHolder createHolder(ViewGroup parent, int type) {
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, type, parent, false);
            return new DataBindingHolder(binding);
        }
    }


}
