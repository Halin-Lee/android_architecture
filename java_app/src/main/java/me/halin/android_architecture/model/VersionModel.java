package me.halin.android_architecture.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "version")
public class VersionModel {
    @SerializedName("MIN_VERSION")
    private int minVersion;
    @SerializedName("NEW_VERSION")
    private int newVersion;
    @PrimaryKey
    @NonNull
    @Expose
    private String platform;

    private long updateTime;

    public VersionModel(int minVersion, int newVersion, String platform, long updateTime) {
        this.minVersion = minVersion;
        this.newVersion = newVersion;
        this.platform = platform;
        this.updateTime = updateTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getNewVersion() {
        return newVersion;
    }

    public int getMinVersion() {
        return minVersion;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
