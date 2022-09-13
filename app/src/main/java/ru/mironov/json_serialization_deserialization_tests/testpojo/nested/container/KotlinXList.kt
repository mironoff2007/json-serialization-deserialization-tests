package ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.TestObjectKotlinSerialization

@Serializable
class KotlinXList (
    @SerialName("list")
    val list: List<TestObjectKotlinSerialization>
    ) {

}