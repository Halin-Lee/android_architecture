package me.halin.android_architecture.main;

import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import java.util.List;

import me.halin.android_architecture.R;
import me.halin.android_architecture.model.VersionModel;
import me.halin.android_architecture.uikit.BaseDiffAdapter;
import me.halin.android_architecture.uikit.DataBindingHolder;

public class MainAdapter extends BaseDiffAdapter<VersionModel> {


    public MainAdapter(LayoutInflater inflater) {
        super(new DataBindingHolder.BaseFactory(inflater) {

            @Override
            public int getViewType(Object model) {
                return R.layout.activity_main_item;
            }
        }, new VersionModelDiffFactory());
    }


    public static class VersionModelDiffFactory implements DiffUtilFactory<VersionModel> {


        @Override
        public DiffUtil.Callback buildDiffUtil(final List<VersionModel> oldList, final List<VersionModel> newList) {
            return new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return oldList.size();
                }

                @Override
                public int getNewListSize() {
                    return newList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    VersionModel oldModel = oldList.get(oldItemPosition);
                    VersionModel newModel = newList.get(newItemPosition);
                    return oldModel.getPlatform().equals(newModel.getPlatform());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    VersionModel oldModel = oldList.get(oldItemPosition);
                    VersionModel newModel = newList.get(newItemPosition);
                    Gson gson = new Gson();
                    String oldJson = gson.toJson(oldModel, VersionModel.class);
                    String newJson = gson.toJson(newModel, VersionModel.class);
                    return oldJson.equals(newJson);
                }
            };
        }
    }


}
