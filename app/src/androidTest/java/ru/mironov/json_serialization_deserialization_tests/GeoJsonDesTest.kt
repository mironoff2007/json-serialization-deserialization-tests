package ru.mironov.json_serialization_deserialization_tests


import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import ru.mironov.json_serialization_deserialization_tests.testpojo.lists.GeoJsonParser
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container.*
import java.io.BufferedReader


class GeoJsonDesTest {

    private var time = 0L

    @get:Rule
    var testName: TestName = TestName()
    
    var jsonString = ""

    @Before
    fun before() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val stream = context.resources.openRawResource(R.raw.geojson)
        jsonString = stream.bufferedReader().use(BufferedReader::readText)
    }

    @Test
    fun gsonTest() {
        Log.d("Test_tag", testName.methodName)
        
        val geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.GSON)

        assert(geoJson.features?.isNotEmpty() ?: false)
    }


    @Test
    fun gsonTestWithoutAnnotations() {
        Log.d("Test_tag", testName.methodName)

        val geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.GSON_WO_AN)

        assert(geoJson.features?.isNotEmpty() ?: false)
    }

    @Test
    fun kotlinSerializationTest() {
        Log.d("Test_tag", testName.methodName)

        val geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.KOTLINX)

        assert(geoJson.features?.isNotEmpty() ?: false)
    }

    @Test
    fun jacksonTest() {
        Log.d("Test_tag", testName.methodName)

        val geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.JACKSON)

        assert(geoJson.features?.isNotEmpty() ?: false)
    }

    @Test
    fun moshiTest() {
        Log.d("Test_tag", testName.methodName)

        val geoJson = GeoJsonParser.parse(jsonString, GeoJsonParser.Parser.MOSHI)

        assert(geoJson.features?.isNotEmpty() ?: false)
    }
    
}

