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

class JsonDesTest2 {

    private var time = 0L

    private val testObject = TestObjectGson()

    private val listObjectsStrings = mutableListOf<String>()

    private var jsonString = ""

    private val repeatCount = 200
    private val innerClasses = 10

    @get:Rule
    var testName: TestName = TestName()

    @Before
    fun before() {

        listObjectsStrings.clear()

        var currentObject = testObject
        repeat(repeatCount) { ind ->
            repeat(innerClasses) {
                val innerObject = TestObjectGson(
                    field1 = "value $ind",
                    field2 = ind,
                    field3 = ind.rem(2) == 0,
                    field4 = 0.0 + it.toDouble(),
                    field5 = 1000000L + ind * 100000L
                )
                currentObject.innerClass = innerObject
                currentObject = currentObject.innerClass!!
            }
            jsonString = Gson().toJson(testObject)

            listObjectsStrings.add(jsonString)
        }

        println()
    }


    @Test
    fun gsonTest() {
        val gson = Gson()

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            gson.fromJson(it, object : TypeToken<TestObjectGson>() {}.type)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(true)
    }

    @Test
    fun gsonTestWithoutAnnotations() {
        val gson = Gson()

        time = System.currentTimeMillis()

       listObjectsStrings.forEach {
           gson.fromJson(it, object : TypeToken<TestObjectGsonWoAn>() {}.type)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(true)
    }

    @Test
    fun kotlinSerializationTest() {

        val format = Json { ignoreUnknownKeys = true }
        val serializer = TestObjectKotlinSerialization.serializer()

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            format.decodeFromString(serializer, it)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(true)
    }

    @Test
    fun jacksonTest() {
        val mapper = ObjectMapper()

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            mapper.readValue(it, TestObjectJackson::class.java)
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(true)
    }


}
