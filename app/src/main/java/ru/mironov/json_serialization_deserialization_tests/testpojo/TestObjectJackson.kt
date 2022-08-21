package ru.mironov.json_serialization_deserialization_tests.testpojo


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

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

    @field:JsonProperty("field5")
    @param:JsonProperty("field5")
    var field5: Long = 10000L,

    @field:JsonProperty("field6")
    @param:JsonProperty("field6")
    var field6: TestObjectKotlinSerialization? = null,

    @field:JsonProperty("innerClass")
    @param:JsonProperty("innerClass")
    var innerClass: TestObjectJackson? = null
) {

}