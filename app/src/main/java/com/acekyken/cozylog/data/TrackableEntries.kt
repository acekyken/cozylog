package com.acekyken.cozylog.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acekyken.cozylog.Trackable
import com.acekyken.cozylog.TrackableType
import com.acekyken.cozylog.TrackableValue
import java.util.Date

@Entity("entries")
data class TrackableEntries(@PrimaryKey val id: Int, val type: Trackable, val date: Date, val value: TrackableValue )

// the date should just be the date, without any additional time stamp!