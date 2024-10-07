package com.acekyken.cozylog.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acekyken.cozylog.TrackableType
import com.acekyken.cozylog.TrackableValue
import java.util.Date

@Entity
data class TrackableEntries(@PrimaryKey val id: Int, val type: TrackableType, val date: Date, val value: TrackableValue )
