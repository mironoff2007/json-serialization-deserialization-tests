package ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container

import com.fasterxml.jackson.annotation.JsonProperty
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.TestObjectGson

class JacksonList (
    @field:JsonProperty("list")
    @param:JsonProperty("list")
    val list: List<TestObjectGson>
    ) {

}