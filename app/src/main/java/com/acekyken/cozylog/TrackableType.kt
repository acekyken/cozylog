package com.acekyken.cozylog

// The habit or symptom can have various types, e.g. you can track if a symptom was present or not, or you can track how severe it was, or just take some notes on it, or pick in a 2d coordinate system (this would need some kind of reference, so far only planned for mood with emotional wheel)
enum class TrackableType {
    BINARY, LINEAR, COORDINATE, TEXT
}