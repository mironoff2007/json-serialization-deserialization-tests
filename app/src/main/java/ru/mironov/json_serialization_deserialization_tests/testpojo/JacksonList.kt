package ru.mironov.json_serialization_deserialization_tests.testpojo

import com.fasterxml.jackson.annotation.JsonProperty

class JacksonList (
    @field:JsonProperty("list")
    @param:JsonProperty("list")
    val list: List<TestObjectGson>
    ) {

}