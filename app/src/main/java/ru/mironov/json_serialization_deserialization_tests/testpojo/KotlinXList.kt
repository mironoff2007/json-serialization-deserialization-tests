package ru.mironov.json_serialization_deserialization_tests.testpojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class KotlinXList (
    @SerialName("list")
    val list: List<TestObjectKotlinSerialization>
    ) {

}