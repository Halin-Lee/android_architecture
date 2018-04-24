package me.halin.android_architecture.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {VersionModel.class}, version = 1)
public abstract class VersionModelDatabase extends RoomDatabase {

    public abstract VersionModelDao versionModelDao();

}
