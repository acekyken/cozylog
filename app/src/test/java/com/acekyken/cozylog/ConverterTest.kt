package com.acekyken.cozylog

import com.acekyken.cozylog.data.database.Converters
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ConverterTest {

    private lateinit var converters: Converters

    @Before
    fun initialConvert() {
        converters = Converters()
    }

    @Test
    fun testFromTrackableValue() {
        val bool = TrackableValue.BinaryTrackable(true)
        val numeric = TrackableValue.NumericTrackable(4.5F)
        val text = TrackableValue.TextTrackable("This is a test.")
        val coordinate = TrackableValue.CoordinateTrackable(4.5F,5.0F)

        assertEquals("boolean:true", converters.fromTrackableValue(bool))
        assertEquals("numeric:4.5", converters.fromTrackableValue(numeric))
        assertEquals("text:This is a test.", converters.fromTrackableValue(text))
        assertEquals("coordinate:4.5,5.0", converters.fromTrackableValue(coordinate))
    }

    @Test
    fun testToTrackableValue() {
        val bool = "boolean:true"
        val numeric= "numeric:4.5"
        val text = "text:This is a test."
        val coordinate = "coordinate:4.5,5.0"

        assertEquals(TrackableValue.BinaryTrackable(true),converters.toTrackableValue(bool))
        assertEquals(TrackableValue.NumericTrackable(4.5F), converters.toTrackableValue(numeric))
        assertEquals(TrackableValue.TextTrackable("This is a test."), converters.toTrackableValue(text))
        assertEquals(TrackableValue.CoordinateTrackable(4.5F,5.0F), converters.toTrackableValue(coordinate))
    }

    @Test
    fun testFromTrackableType() {
        assertEquals("\"BINARY\"", converters.fromTrackableType(TrackableType.BINARY))
        assertEquals("\"LINEAR\"", converters.fromTrackableType(TrackableType.LINEAR))
        assertEquals("\"COORDINATE\"", converters.fromTrackableType(TrackableType.COORDINATE))
        assertEquals("\"TEXT\"", converters.fromTrackableType(TrackableType.TEXT))
    }
}