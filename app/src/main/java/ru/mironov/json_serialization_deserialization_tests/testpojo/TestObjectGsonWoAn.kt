package ru.mironov.json_serialization_deserialization_tests.testpojo

data class TestObjectGsonWoAn(
    var field1: String? = "value1",

    var field2: Int = 2,

    var field3: Boolean = true,

    var field4: Double = 4.0,

    var field5: Long = 10000L,

    var field6: TestObjectKotlinSerialization? = null,

    var innerClass: TestObjectGsonWoAn? = null
) {

}