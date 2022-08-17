package ru.mironov.json_serialization_deserialization_tests.testpojo


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TestObjectJackson(
    @field:JsonProperty("field1")
    @param:JsonProperty("field1")
    var field1: String? = "value1",

    @field:JsonProperty("field2")
    @param:JsonProperty("field2")
    var field2: Int = 2,

    @field:JsonProperty("field3")
    @param:JsonProperty("field3")
    var field3: Boolean = true,

    @field:JsonProperty("field4")
    @param:JsonProperty("field4")
    var field4: Double = 4.0,

    @field:JsonProperty("innerClass")
    @param:JsonProperty("innerClass")
    var innerClass: TestObjectJackson? = null,
) {

}