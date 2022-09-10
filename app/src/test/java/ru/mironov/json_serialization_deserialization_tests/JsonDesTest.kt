package ru.mironov.json_serialization_deserialization_tests

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
import ru.mironov.json_serialization_deserialization_tests.testpojo.*

class JsonDesTest {

    private var time = 0L

    private val listObjectsStrings = mutableListOf<String>()

    private var jsonString = ""

    private val repeatCount = 5000
    private val innerClasses = 20

    @get:Rule
    var testName: TestName = TestName()

    val gson = Gson()

    init {
        gson.serializeNulls()
    }

    @Before
    fun before() {
        listObjectsStrings.clear()
        repeat(repeatCount) { ind ->
            var testObject = TestObjectGson()
            var currentObject = testObject
            repeat(innerClasses) {
                val innerObject = TestObjectGson(
                    field1 = "value ${it + ind}",
                    field2 = it + ind * 10,
                    field3 = ind.rem(2) == 0,
                    field4 = ind + it + 0.0,
                    field5 = 1000000L + it + ind * 10
                )
                currentObject.innerClass = innerObject
                currentObject = currentObject.innerClass!!
            }
            jsonString = gson.toJson(testObject)
            listObjectsStrings.add(jsonString)
        }
        println()
    }

    @Test
    fun gsonTest() {
        var obj : TestObjectGson? = null

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            obj = gson.fromJson(it, object : TypeToken<TestObjectGson>() {}.type)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(obj?.field6 == null)
    }

    @Test
    fun gsonTestWithoutAnnotations() {
        var obj : TestObjectGsonWoAn? = null

        time = System.currentTimeMillis()

       listObjectsStrings.forEach {
           gson.fromJson(it, object : TypeToken<TestObjectGsonWoAn>() {}.type)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(obj?.field6 == null)
    }

    @Test
    fun kotlinSerializationTest() {
        var obj : TestObjectKotlinSerialization? = null

        val format = Json { ignoreUnknownKeys = true }
        val serializer = TestObjectKotlinSerialization.serializer()

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            obj = format.decodeFromString(serializer, it)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(obj?.field6 == null)
    }

    @Test
    fun jacksonTest() {
        var obj : TestObjectJackson? = null
        val mapper = ObjectMapper()

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            obj = mapper.readValue(it, TestObjectJackson::class.java)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(obj?.field6 == null)
    }

    @Test
    fun moshiTest() {
        val  moshi =  Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<TestObjectMoshi> = moshi.adapter(TestObjectMoshi::class.java)

        var obj: TestObjectMoshi? = null

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            obj = jsonAdapter.fromJson(it)!!
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(obj?.field6 == null)
    }


}
