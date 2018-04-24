package me.halin.android_architecture.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "version")
class VersionModel(@SerializedName("MIN_VERSION")
                   var minVersion: Int,

                   @SerializedName("NEW_VERSION")
                   var newVersion: Int,

                   @PrimaryKey
                   @NonNull
                   @Expose var platform: String,

                   var updateTime: Long) {


}