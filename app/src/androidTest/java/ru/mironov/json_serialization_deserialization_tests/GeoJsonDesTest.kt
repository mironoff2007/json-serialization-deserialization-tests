package ru.mironov.json_serialization_deserialization_tests


import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import ru.mironov.json_serialization_deserialization_tests.testpojo.geojson.GeoJson
import ru.mironov.json_serialization_deserialization_tests.testpojo.geojson.GeoJsonParser
import java.io.BufferedReader

class GeoJsonDesTest {

    private var repeatParse = 5

    lateinit var geoJson: GeoJson

    @get:Rule
    var testName: TestName = TestName()
    
    private var jsonString = ""

    private val testTag = "Test_tag"

    @Before
    fun before() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val stream = context.resources.openRawResource(R.raw.geojson)
        jsonString = stream.bufferedReader().use(BufferedReader::readText)
    }

    @Test
    fun gsonTest() {
        Log.d(testTag, testName.methodName)

        repeat(repeatParse) {
            geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.GSON)
        }

        assert(geoJson.features?.isNotEmpty() ?: false)
    }


    @Test
    fun gsonTestWithoutAnnotations() {
        Log.d(testTag, testName.methodName)

        repeat(repeatParse) {
            geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.GSON_WO_AN)
        }

        assert(geoJson.features?.isNotEmpty() ?: false)
    }

    @Test
    fun kotlinSerializationTest() {
        Log.d(testTag, testName.methodName)

        repeat(repeatParse) {
            geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.KOTLINX)
        }

        assert(geoJson.features?.isNotEmpty() ?: false)
    }

    @Test
    fun jacksonTest() {
        Log.d(testTag, testName.methodName)

        repeat(repeatParse) {
            geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.JACKSON)
        }

        assert(geoJson.features?.isNotEmpty() ?: false)
    }

    @Test
    fun moshiTest() {
        Log.d(testTag, testName.methodName)

        repeat(repeatParse) {
            geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.MOSHI)
        }

        assert(geoJson.features?.isNotEmpty() ?: false)
    }
    
}

