package com.acekyken.cozylog.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acekyken.cozylog.TrackableType

@Entity
data class Trackable(@PrimaryKey val id: Int, val name: String, val type: TrackableType, val color: Int, val text: String?)
