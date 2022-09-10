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

    private val listObjectsStrings = mutableListOf<String>()

    private var jsonString = ""

    private val listSize = 10
    private val innerClasses = 500

    private val repeatTestCount = 5000

    @get:Rule
    var testName: TestName = TestName()

    val gson = Gson()

    init {
        println("init")
        gson.serializeNulls()
    }

    @Before
    fun before() {
        listObjectsStrings.clear()
        repeat(listSize) { i -> val ind = i*innerClasses
            var testObject = TestObjectGson( //field1 = "value $i",
                field2 = i,
                field3 = i.rem(2) == 0,
                field4 = 0.0 + ind +i,
                field5 = 1000000L + i + i)
            var currentObject = testObject
            repeat(innerClasses) {
                val innerObject = TestObjectGson(
                    //field1 = "value $it+$ind",
                    field2 = 10+it+ind,
                    field3 = ind.rem(2) == 0,
                    field4 = 0.0 + ind +it,
                    field5 = 1000000L + it + ind
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

        val timeList = mutableListOf<Long>()

        time = System.currentTimeMillis()

        //val myobj:String = gson.fromJson("123456789qwertyuiopoasdfghjklzxcvbnm", object : TypeToken<String>() {}.type)

        listObjectsStrings.forEach {
            val timeEach = System.currentTimeMillis()
            obj = gson.fromJson(it, object : TypeToken<TestObjectGson>() {}.type)
            timeList.add(System.currentTimeMillis() - timeEach)
        }
        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }
        println(testName.methodName + " avg-;" + (System.currentTimeMillis() - time))
        assert(obj?.innerClass?.field1 != null)
    }

    @Test
    fun gsonRepeatSameOneGsonTest() {
        var obj : TestObjectGson? = null

        val timeList = mutableListOf<Long>()

        val str = listObjectsStrings.first()
        repeat(repeatTestCount){
            val timeEach = System.currentTimeMillis()
            obj = gson.fromJson( str, object : TypeToken<TestObjectGson>() {}.type)
            timeList.add(System.currentTimeMillis() - timeEach)
        }
        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }

        val sumTime = timeList.sumOf { it }

        println(testName.methodName + " avg-;" + sumTime)

        assert(true)
    }

    @Test
    fun gsonRepeatTest() {
        var obj : TestObjectGson? = null

        val timeList = mutableListOf<Long>()

        repeat(listSize) {
            time = System.currentTimeMillis()
            listObjectsStrings.forEach {
                obj = gson.fromJson(it, object : TypeToken<TestObjectGson>() {}.type)
            }

            val newTime = System.currentTimeMillis() - time
            timeList.add(newTime)
        }

        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }
        //println(testName.methodName + " avg-;" + (System.currentTimeMillis() - time))


        assert(obj?.innerClass?.field1 != null)
    }

    @Test
    fun gsonTestWithoutAnnotations() {
        var obj : TestObjectGsonWoAn? = null

        time = System.currentTimeMillis()

       listObjectsStrings.forEach {
           gson.fromJson(it, object : TypeToken<TestObjectGsonWoAn>() {}.type)
        }

        println(testName.methodName + " avg-;" + (System.currentTimeMillis() - time))
        assert(obj?.field6 == null)
    }

    @Test
    fun kotlinSerializationTest() {

        var obj : TestObjectKotlinSerialization? = null

        val format = Json { ignoreUnknownKeys = true }
        val serializer = TestObjectKotlinSerialization.serializer()

        time = System.currentTimeMillis()

        val timeList = mutableListOf<Long>()
        listObjectsStrings.forEach {
            val timeEach = System.currentTimeMillis()
            obj = format.decodeFromString(serializer, it)
            timeList.add(System.currentTimeMillis() - timeEach)
        }
        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }

        println(testName.methodName + " avg-;" + (System.currentTimeMillis() - time))
        assert(obj?.field6 == null)
    }

    @Test
    fun kotlinSerializationSameOneTest() {
        var obj : TestObjectKotlinSerialization? = null

        val format = Json { ignoreUnknownKeys = true }
        val serializer = TestObjectKotlinSerialization.serializer()

        time = System.currentTimeMillis()

        val timeList = mutableListOf<Long>()
        listObjectsStrings.forEach {
            val timeEach = System.currentTimeMillis()
            obj = format.decodeFromString(serializer, it)
            timeList.add(System.currentTimeMillis() - timeEach)
        }
        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }


        println(testName.methodName + " avg-;" + (System.currentTimeMillis() - time))
        assert(obj?.field6 == null)
    }

    @Test
    fun jacksonTest() {
        var obj : TestObjectJackson? = null
        val mapper = ObjectMapper()

        time = System.currentTimeMillis()

        val timeList = mutableListOf<Long>()
        listObjectsStrings.forEach {
            val timeEach = System.currentTimeMillis()
            obj = mapper.readValue(it, TestObjectJackson::class.java)
            timeList.add(System.currentTimeMillis() - timeEach)
        }
        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }

        println(testName.methodName + " avg-;" + (System.currentTimeMillis() - time))
        assert(obj?.field6 == null)
    }

    @Test
    fun jacksonRepeatSameOneTest() {
        var obj : TestObjectJackson? = null
        val mapper = ObjectMapper()

        time = System.currentTimeMillis()

        val timeList = mutableListOf<Long>()
        val str = listObjectsStrings.first()
        repeat(repeatTestCount) {
            val timeEach = System.currentTimeMillis()
            obj = mapper.readValue(str, TestObjectJackson::class.java)
            timeList.add(System.currentTimeMillis() - timeEach)
        }
        timeList.forEach {
            println(testName.methodName + " -;" + (it))
        }

        println(testName.methodName + " avg-;" + timeList.sumOf { it })
        assert(true)
    }

}
