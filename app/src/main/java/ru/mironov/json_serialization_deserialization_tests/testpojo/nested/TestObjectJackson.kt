package ru.mironov.json_serialization_deserialization_tests.testpojo.nested


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

    @field:JsonProperty("field5")
    @param:JsonProperty("field5")
    var field5: Long = 10000L,

    @field:JsonProperty("field6")
    @param:JsonProperty("field6")
    var field6: TestObjectKotlinSerialization? = null,

    @field:JsonProperty("field7")
    @param:JsonProperty("field7")
    var field7: String? = null,

    @field:JsonProperty("field8")
    @param:JsonProperty("field8")
    var field8: Byte? = null,

    @field:JsonProperty("innerClass")
    @param:JsonProperty("innerClass")
    var innerClass: TestObjectJackson? = null
) {

}