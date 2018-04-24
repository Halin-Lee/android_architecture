package me.halin.android_architecture.main

import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import com.google.gson.Gson
import me.halin.android_architecture.R
import me.halin.android_architecture.model.VersionModel
import me.halin.android_architecture.uikit.BaseDiffAdapter
import me.halin.android_architecture.uikit.DataBindingHolder

class MainAdapter(layoutInflater: LayoutInflater) : BaseDiffAdapter<VersionModel>(VersionModelHolderFactory(layoutInflater), VersionModelDiffFactory()) {

    class VersionModelHolderFactory(layoutInflater: LayoutInflater) : DataBindingHolder.BaseFactory<VersionModel>(layoutInflater) {
        override fun getViewType(model: VersionModel): Int {
            return R.layout.activity_main_item
        }
    }

    class VersionModelDiffFactory : DiffUtilFactory<VersionModel> {
        override fun buildDiffUtil(oldList: List<VersionModel>, newList: List<VersionModel>): DiffUtil.Callback {
            return object : DiffUtil.Callback() {

                override fun getOldListSize(): Int {
                    return oldList.size
                }

                override fun getNewListSize(): Int {
                    return newList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldModel = oldList[oldItemPosition]
                    val newModel = newList[newItemPosition]
                    return oldModel.platform.equals(newModel.platform)
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldModel = oldList[oldItemPosition]
                    val newModel = newList[newItemPosition]
                    val gson = Gson()
                    val oldJson = gson.toJson(oldModel)
                    val newJson = gson.toJson(newModel)
                    return oldJson.equals(newJson)
                }


            }
        }

    }
}