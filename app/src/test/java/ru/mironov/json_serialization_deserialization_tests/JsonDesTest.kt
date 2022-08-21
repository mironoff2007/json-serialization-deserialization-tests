package ru.mironov.json_serialization_deserialization_tests

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import ru.mironov.json_serialization_deserialization_tests.testpojo.*

class JsonDesTest {

    private var time = 0L

    private val testObject = TestObjectGson()

    private var jsonString = ""

    private val repeatCount = 5000
    private val innerClasses = 200

    @get:Rule
    var testName: TestName = TestName()

    @Before
    fun before(){

        var currentObject = testObject
        repeat(innerClasses) {
            val innerObject = TestObjectGson(
                field1 = "value $it",
                field2 = it,
                field3 = it.rem(2) == 0,
                field4 = 0.0 + it.toDouble(),
                field5 = 1000000L + it*100000L
            )
            currentObject.innerClass = innerObject
            currentObject = currentObject.innerClass!!
        }

        jsonString = Gson().toJson(testObject)
        println()
    }


    @Test
    fun gsonTest() {
        val gson = Gson()

        time = System.currentTimeMillis()

        var testObject: TestObjectGson =
            gson.fromJson(jsonString, object : TypeToken<TestObjectGson>() {}.type)

        println(testName.methodName + " first run-" + (System.currentTimeMillis() - time) + "ms")

        time = System.currentTimeMillis()

        repeat(repeatCount) {
            testObject = gson.fromJson(jsonString, object : TypeToken<TestObjectGson>() {}.type)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(testObject.innerClass != null)
    }

    @Test
    fun gsonTestWithoutAnnotations() {
        val gson = Gson()

        time = System.currentTimeMillis()

        var testObject: TestObjectGsonWoAn = gson.fromJson(jsonString, object : TypeToken<TestObjectGsonWoAn>() {}.type)

        println(testName.methodName + " first run-" + (System.currentTimeMillis() - time) + "ms")

        time = System.currentTimeMillis()

        repeat(repeatCount) {
           testObject = gson.fromJson(jsonString, object : TypeToken<TestObjectGsonWoAn>() {}.type)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(testObject.innerClass != null)
    }

    @Test
    fun kotlinSerializationTest() {
        var testObject: TestObjectKotlinSerialization
        val format = Json { ignoreUnknownKeys = true }
        val serializer = TestObjectKotlinSerialization.serializer()

        time = System.currentTimeMillis()
        testObject = format.decodeFromString(serializer, jsonString)

        println(testName.methodName + " first run-" + (System.currentTimeMillis() - time) + "ms")

        time = System.currentTimeMillis()

        repeat(repeatCount) {
            testObject =  format.decodeFromString(serializer, jsonString)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(testObject.innerClass != null)
    }

    @Test
    fun jacksonTest() {
        val mapper = ObjectMapper()

        time = System.currentTimeMillis()
        var testObject: TestObjectJackson = mapper.readValue(jsonString, TestObjectJackson::class.java)

        println(testName.methodName + " first run-" + (System.currentTimeMillis() - time) + "ms")

        time = System.currentTimeMillis()

        repeat(repeatCount) {
            testObject = mapper.readValue(jsonString, TestObjectJackson::class.java)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(testObject.innerClass != null)
    }


}
