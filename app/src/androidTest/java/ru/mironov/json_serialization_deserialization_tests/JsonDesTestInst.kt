package ru.mironov.json_serialization_deserialization_tests

import android.app.Instrumentation
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
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.TestDataProvider
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container.*
import java.io.BufferedReader

class JsonDesTestInst {

    private var time = 0L

    private val repeatTestCount = 20
    private val listSize = 100
    private val innerClasses = 200

    private var jsonStringOfList = ""//TestDataProvider.generateListString(listSize, innerClasses)

    private val timeList = mutableListOf<Long>()
    @get:Rule
    var testName: TestName = TestName()

    @Before
    fun before() {
        timeList.clear()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val stream = context.resources.openRawResource(R.raw.nested)
        jsonStringOfList = stream.bufferedReader().use(BufferedReader::readText)
    }

    private fun printResult(testName: String){
        val strB = StringBuilder()
        strB.append(testName)
        timeList.forEach { strB.append(";$it") }
        Log.d("Test_tag", strB.toString())
    }

    @Test
    fun gsonTest() {
        val gson = Gson()
        gson.serializeNulls()

        var obj : GsonList? = null

        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = gson.fromJson(jsonStringOfList, object : TypeToken<GsonList>() {}.type)
            timeList.add(System.currentTimeMillis() - time)
        }

        printResult(testName.methodName)

        assert((obj?.list?.size ?: 0) == listSize)
    }


    @Test
    fun gsonTestWithoutAnnotations() {
        val gson = Gson()
        gson.serializeNulls()

        var obj: GsonWoAnList? = null

        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = gson.fromJson(jsonStringOfList, object : TypeToken<GsonWoAnList>() {}.type)
            timeList.add(System.currentTimeMillis() - time)
        }

        printResult(testName.methodName)

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun kotlinSerializationTest() {
        var obj : KotlinXList? = null

        val format = Json { ignoreUnknownKeys = true }
        val serializer = KotlinXList.serializer()

        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = format.decodeFromString(serializer, jsonStringOfList)
            timeList.add(System.currentTimeMillis() - time)
        }

        printResult(testName.methodName)

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun jacksonTest() {
        var obj : JacksonList? = null
        val mapper = ObjectMapper()


        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = mapper.readValue(jsonStringOfList, JacksonList::class.java)
            timeList.add(System.currentTimeMillis() - time)
        }

        printResult(testName.methodName)

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun moshiTest() {
        val  moshi =  Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<MoshiList> = moshi.adapter(MoshiList::class.java)

        var obj: MoshiList? = null

        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = jsonAdapter.fromJson(jsonStringOfList)!!
            timeList.add(System.currentTimeMillis() - time)
        }

        printResult(testName.methodName)

        assert((obj?.list?.size ?: 0) == listSize)
    }


}
