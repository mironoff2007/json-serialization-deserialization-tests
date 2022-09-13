package ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.TestObjectMoshi

@JsonClass(generateAdapter = true)
class MoshiList (
    @Json(name = "list")
    val list: List<TestObjectMoshi>
    ) {

}