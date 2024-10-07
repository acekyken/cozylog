package com.acekyken.cozylog.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acekyken.cozylog.data.Trackable
import com.acekyken.cozylog.data.TrackableEntries

@Database(entities = [TrackableEntries::class, Trackable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackableDao(): TrackableDao
}