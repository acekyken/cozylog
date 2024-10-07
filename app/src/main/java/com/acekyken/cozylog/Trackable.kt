package com.acekyken.cozylog

import kotlinx.serialization.Serializable

// This describes a generic Trackable Element. It has a type and a value that can be displayed, and it should result in different view.
@Serializable
data class Trackable(val type: TrackableType, val name: String, val value: TrackableValue)