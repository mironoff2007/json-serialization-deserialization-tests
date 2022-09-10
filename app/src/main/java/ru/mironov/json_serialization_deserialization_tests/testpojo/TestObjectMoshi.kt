package ru.mironov.json_serialization_deserialization_tests.testpojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestObjectMoshi(
    @Json(name = "field1")
    var field1: String? = "value",

    @Json(name = "field2")
    var field2: Int = -1,

    @Json(name = "field3")
    var field3: Boolean = true,

    @Json(name = "field4")
    var field4: Double = -1.0,

    @Json(name = "field5")
    var field5: Long = 10000L,

    @Json(name = "field6")
    var field6: TestObjectMoshi? = null,

    @Json(name = "innerClass")
    var innerClass: TestObjectMoshi? = null

    ) {

}