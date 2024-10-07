package com.acekyken.cozylog.data.database

import androidx.room.Dao
import androidx.room.Query
import com.acekyken.cozylog.data.TrackableEntries
import java.util.Date

@Dao
interface TrackableDao {
    @Query("SELECT * FROM entries")
    fun getAll(): List<TrackableEntries>

    @Query("SELECT * FROM entries WHERE date LIKE :checkdate")
    fun findByDate(checkdate: Date): List<TrackableEntries>

}