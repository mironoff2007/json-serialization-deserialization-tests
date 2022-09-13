package ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container

import com.google.gson.annotations.SerializedName
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.TestObjectGson

class GsonList (
    @SerializedName("list")
    val list: List<TestObjectGson>
    ) {

}