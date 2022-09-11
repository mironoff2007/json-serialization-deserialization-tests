package ru.mironov.json_serialization_deserialization_tests.testpojo

import com.google.gson.annotations.SerializedName

class GsonList (
    @SerializedName("list")
    val list: List<TestObjectGson>
    ) {

}