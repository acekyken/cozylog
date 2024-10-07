package com.acekyken.cozylog.data.database

import androidx.room.TypeConverter
import com.acekyken.cozylog.TrackableType
import com.acekyken.cozylog.TrackableValue
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.util.Date
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it); }
    }

    @TypeConverter
    fun toTimestamp(value: Date?): Long? {
        return value?.time?.toLong();
    }

    @TypeConverter
    fun fromTrackableType(value: TrackableType?): String? {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toTrackableType(value: String?): TrackableType? {
        return value?.let { Json.decodeFromString(it) };
    }

    @TypeConverter
    fun fromTrackableValue(value: TrackableValue): String? {
        return when (value) {
            is TrackableValue.BinaryTrackable -> {
                "boolean:${value.value.toString()}"
            }

            is TrackableValue.NumericTrackable -> {
                "numeric:${value.value.toString()}"
            }

            is TrackableValue.CoordinateTrackable -> {
                "coordinate:${value.x},${value.y}"
            }

            is TrackableValue.TextTrackable -> {
                "text:${value.value.toString()}"
            }

            else -> {
                null
            }
        }
    }

    @TypeConverter
    fun toTrackableValue(value: String?): TrackableValue? {
        return value?.let{
            val parts = value.split(":")
            val type = parts[0]
            val data = parts[1]
            when (type) {
                "boolean" -> TrackableValue.BinaryTrackable(data.toBoolean())
                "numeric" -> TrackableValue.NumericTrackable(data.toFloat())
                "text" -> TrackableValue.TextTrackable(data)
                "coordinate" -> {
                    val coordinateparts = data.split(",")
                    val x = coordinateparts[0].toFloat()
                    val y = coordinateparts[1].toFloat()
                    TrackableValue.CoordinateTrackable(x,y)
                }
                else -> {null}

            }
        }
    }



}
