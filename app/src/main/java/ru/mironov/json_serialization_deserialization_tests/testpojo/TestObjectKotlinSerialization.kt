package ru.mironov.json_serialization_deserialization_tests.testpojo

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestObjectKotlinSerialization(

    @SerialName("field1")
    var field1: String? = null,

    @SerialName("field2")
    var field2: Int = 2,

    @SerialName("field3")
    var field3: Boolean = true,

    @SerialName("field4")
    var field4: Double = 4.0,

    @SerialName("field5")
    var field5: Long = 10000L,

    @SerialName("field6")
    var field6: TestObjectKotlinSerialization? = null,

    @SerialName("innerClass")
    var innerClass: TestObjectKotlinSerialization? = null

    ) {


}