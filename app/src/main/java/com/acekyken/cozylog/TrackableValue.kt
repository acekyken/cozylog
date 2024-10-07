package com.acekyken.cozylog

// A value for the trackable element. These need to go together with the TrackableElementType.
sealed class TrackableValue() {
    class BinaryTrackable(val value: Boolean = false) : TrackableValue() {
        override fun equals(other: Any?):Boolean {
            if (this === other) return true
            if (other !is BinaryTrackable) return false

            return this.value == other.value
        }
    }
    class NumericTrackable(val value: Float = 0.0f): TrackableValue() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is NumericTrackable) return false
            return this.value == other.value
        }
    }
    class TextTrackable(val value: String = ""): TrackableValue() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is TextTrackable) return false

            return this.value == other.value
        }
    }
    //This type is for tracking a position on a 2d coordinate system (over an image, within a 2d shape). Might need to take a coordinate type, if kotlin has anything useful there
    class CoordinateTrackable(val x: Float = 0.0f, val y:Float= 0.0f): TrackableValue() {
        override fun equals(other: Any?): Boolean {
            if (this === other) true
            if (other !is CoordinateTrackable) return false
            return this.x == other.x && this.y == other.y
        }
    }
}