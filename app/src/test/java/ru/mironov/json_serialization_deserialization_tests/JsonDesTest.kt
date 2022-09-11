package ru.mironov.json_serialization_deserialization_tests

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.serializer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import ru.mironov.json_serialization_deserialization_tests.testpojo.*

class JsonDesTest {

    private var time = 0L

    private val repeatTestCount = 1
    private val listSize = 100
    private val innerClasses = 200

    private var jsonStringOfList = TestDataProvider.generateListString(listSize, innerClasses)//TestDataProvider.provideListObjects()


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

        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = gson.fromJson(jsonStringOfList, object : TypeToken<GsonList>() {}.type)
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

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
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

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
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

        assert((obj?.list?.size ?: 0) == listSize)
    }

    @Test
    fun jacksonTest() {
        var obj : JacksonList? = null
        val mapper = ObjectMapper()

        repeat(repeatTestCount) {
            time = System.currentTimeMillis()
            obj = mapper.readValue(jsonStringOfList, JacksonList::class.java)
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

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
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

        assert((obj?.list?.size ?: 0) == listSize)
    }


}
