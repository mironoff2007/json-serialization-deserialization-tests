package ru.mironov.json_serialization_deserialization_tests.testpojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MoshiList (
    @Json(name = "list")
    val list: List<TestObjectMoshi>
    ) {

}