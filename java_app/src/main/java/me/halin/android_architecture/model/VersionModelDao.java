package me.halin.android_architecture.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface VersionModelDao {

    @Insert(onConflict = REPLACE)
    void save(VersionModel versionModel);

    @Query("SELECT * FROM version Where platform =:platform")
    LiveData<VersionModel> load(String platform);

    @Query("SELECT * FROM version order by updateTime")
    LiveData<List<VersionModel>> loadAll();
}
