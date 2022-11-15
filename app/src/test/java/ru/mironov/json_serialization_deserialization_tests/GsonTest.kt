package ru.mironov.json_serialization_deserialization_tests

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

class GsonTest {

    @get:Rule
    var testName: TestName = TestName()

    private val testTag = "Test_tag"

    @Before
    fun before() {

    }

    @Test
    fun gsonTest() {
        println(testName.methodName)

        val gson = GsonBuilder().create()

        //val str = "{\"id\" = 1 , \"params\":{\"name\": \"machine name\"}}"
        val str = "{\"id\" = null , \"params\":{}}"
        //val str ="{}"
        val obj: TestObject = gson.fromJson( str, object : TypeToken<TestObject>() {}.type )
        val json = gson.toJson(obj)
        assertEquals("", obj.id1 )
    }

    class TestObject(
        @Transient
        @SerializedName("id")
        val _id: String? = "init",

        @SerializedName("numb")
        val numb: Double,

        @SerializedName("params")
        val _params: HashMap<String,String>? = null
    ) {
        val id1 by lazy { _id ?: "" }

        val params by lazy { _params ?: hashMapOf() }
    }
}

