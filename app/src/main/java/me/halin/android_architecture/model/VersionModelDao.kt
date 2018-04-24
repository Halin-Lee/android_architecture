package me.halin.android_architecture.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface VersionModelDao {

    @Insert(onConflict = REPLACE)
    fun save(versionModel: VersionModel)

    @Query("SELECT * FROM version Where platform =:platform")
    fun load(platform: String): LiveData<VersionModel>


    @Query("SELECT * FROM version order by platform")
    fun loadAll(): LiveData<List<VersionModel>>

}