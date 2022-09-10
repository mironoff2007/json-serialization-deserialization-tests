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

    private val repeatCount = 50
    private val innerClasses = 200

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
                val numb = it * repeatCount + ind
                val innerObject = TestObjectGson(
                    field1 = "value $numb",
                    field2 = numb,
                    field3 = ind.rem(2) == 0,
                    field4 = numb + 0.1,
                    field5 = 1000000L + numb,
                    field7 = "abc$numb",
                    field8 = ind.toByte()
                )
                currentObject.innerClass = innerObject
                currentObject = currentObject.innerClass!!
            }
            jsonString = gson.toJson(testObject)
            listObjectsStrings.add(jsonString)
        }
    }

    @Test
    fun gsonTest() {
        var obj : TestObjectGson? = null

        listObjectsStrings.forEach {
            time = System.currentTimeMillis()
            obj = gson.fromJson(it, object : TypeToken<TestObjectGson>() {}.type)
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

        assert(true)
    }

    @Test
    fun gsonTestWithoutAnnotations() {
        var obj : TestObjectGsonWoAn? = null

       listObjectsStrings.forEach {
           time = System.currentTimeMillis()
           obj = gson.fromJson(it, object : TypeToken<TestObjectGsonWoAn>() {}.type)
           println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

        assert(true)
    }

    @Test
    fun kotlinSerializationTest() {
        var obj : TestObjectKotlinSerialization? = null

        val format = Json { ignoreUnknownKeys = true }
        val serializer = TestObjectKotlinSerialization.serializer()

        listObjectsStrings.forEach {
            time = System.currentTimeMillis()
            obj = format.decodeFromString(serializer, it)
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

        assert(true)
    }

    @Test
    fun jacksonTest() {
        var obj : TestObjectJackson? = null
        val mapper = ObjectMapper()

        listObjectsStrings.forEach {
            time = System.currentTimeMillis()
            obj = mapper.readValue(it, TestObjectJackson::class.java)
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        }

        assert(true)
    }

    @Test
    fun moshiTest() {
        val  moshi =  Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<TestObjectMoshi> = moshi.adapter(TestObjectMoshi::class.java)

        var obj: TestObjectMoshi? = null

        listObjectsStrings.forEach {
            time = System.currentTimeMillis()
            obj = jsonAdapter.fromJson(it)!!
            println(testName.methodName + ";" + (System.currentTimeMillis() - time))

        }

        assert(true)
    }


}
