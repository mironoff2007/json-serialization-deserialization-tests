package ru.mironov.json_serialization_deserialization_tests


import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import ru.mironov.json_serialization_deserialization_tests.testpojo.geojson.GeoJsonKotlinSerialization

class KotlinXTest {

    @get:Rule
    var testName: TestName = TestName()

    private val testTag = "Test_tag"

    @Before
    fun before() {

    }

    @Test
    fun kotlinxTest() {
        println(testName.methodName)

        val format = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        }
        //gson.serializeNulls()

        val str = "{\"id\" : null}"
        //val str = "{\"id\" = null}"
        //val str ="{}"
        val obj: TestObject = format.decodeFromString(TestObject.serializer(), str)

        val v = format.encodeToString(TestObject.serializer(), obj)

        assert(true)
    }

    @Serializable
    class TestObject(
        @SerialName("id")
        val id: Int?,

       @SerialName("numb")
        val numb: Double?

        //@SerialName("obj")
       // val obj: TestObject?
    ) {
    }
}

