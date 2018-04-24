package me.halin.android_architecture.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [VersionModel::class], version = 1)
abstract class VersionModelDatabase : RoomDatabase() {

    abstract fun versionModelDao(): VersionModelDao
}