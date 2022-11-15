package ru.mironov.json_serialization_deserialization_tests

import android.app.Instrumentation
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

class JsonDesTest {

    private var time = 0L

    private val repeatTestCount = 10
    private val listSize = 50
    private val innerClasses = 200

    //private var jsonStringOfList = TestDataProvider.provideListObjects()
    private var jsonStringOfList = TestDataProvider.generateListString(listSize, innerClasses)

    @get:Rule
    var testName: TestName = TestName()

    @Before
    fun before() {

    }

    @Test
    fun gsonTest() {
        val gson = Gson()
        gson.serializeNulls()

        var obj : GsonList? = null

        println()
        print(testName.methodName)
        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = gson.fromJson(jsonStringOfList, object : TypeToken<GsonList>() {}.type)
            print(";" + (System.currentTimeMillis() - time))
        }

        assert((obj?.list?.size ?: 0) == listSize)
    }


    @Test
    fun gsonTestWithoutAnnotations() {
        val gson = Gson()
        gson.serializeNulls()

        var obj: GsonWoAnList? = null

        println()
        print(testName.methodName)
        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = gson.fromJson(jsonStringOfList, object : TypeToken<GsonWoAnList>() {}.type)
            print(";" + (System.currentTimeMillis() - time))
        }

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun kotlinSerializationTest() {
        var obj : KotlinXList? = null

        val format = Json { ignoreUnknownKeys = true }
        val serializer = KotlinXList.serializer()

        println()
        print(testName.methodName)
        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = format.decodeFromString(serializer, jsonStringOfList)
            print(";" + (System.currentTimeMillis() - time))
        }

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun jacksonTest() {
        var obj : JacksonList? = null
        val mapper = ObjectMapper()

        println()
        print(testName.methodName)
        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = mapper.readValue(jsonStringOfList, JacksonList::class.java)
            print(";" + (System.currentTimeMillis() - time))
        }
        println()

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun moshiTest() {
        val  moshi =  Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<MoshiList> = moshi.adapter(MoshiList::class.java)

        var obj: MoshiList? = null

        println()
        print(testName.methodName)
        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = jsonAdapter.fromJson(jsonStringOfList)!!
            print(";" + (System.currentTimeMillis() - time))
        }

        assert((obj?.list?.size ?: 0) == listSize)
    }


}
