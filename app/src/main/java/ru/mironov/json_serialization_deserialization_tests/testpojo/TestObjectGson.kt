package ru.mironov.json_serialization_deserialization_tests.testpojo


import com.google.gson.annotations.SerializedName

data class TestObjectGson(
    @SerializedName("field1")
    var field1: String? = null,

    @SerializedName("field2")
    var field2: Int = -1,

    @SerializedName("field3")
    var field3: Boolean = true,

    @SerializedName("field4")
    var field4: Double = -1.0,

    @SerializedName("field5")
    var field5: Long = 10000L,

    @SerializedName("field6")
    var field6: TestObjectGson? = null,

    @SerializedName("innerClass")
    var innerClass: TestObjectGson? = null

    ) {

}