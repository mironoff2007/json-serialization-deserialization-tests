package ru.mironov.json_serialization_deserialization_tests.testpojo.nested

import com.google.gson.Gson
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.TestObjectGson
import ru.mironov.json_serialization_deserialization_tests.testpojo.nested.container.GsonList

object TestDataProvider {

    fun generateListString(listSize: Int, innerObjectsCount: Int):String {
        val gson = Gson()
        gson.serializeNulls()

        val listObjects = mutableListOf<TestObjectGson>()
        var jsonStringOfList = ""

        repeat(listSize) { ind ->
            val testObject = TestObjectGson()
            var currentObject = testObject
            repeat(innerObjectsCount) {
                val numb = it * listSize + ind
                val innerObject = TestObjectGson(
                    field1 = "value $numb",
                    field2 = numb,
                    field3 = ind.rem(2) == 0,
                    field4 = numb + 0.1,
                    field5 = 1000000L + numb,
                    field7 = "abc$numb",
                    field8 = numb.toByte()
                )
                currentObject.innerClass = innerObject
                currentObject = currentObject.innerClass!!
            }

            listObjects.add(testObject)
            jsonStringOfList = gson.toJson(GsonList(listObjects))
        }

        return jsonStringOfList
    }

}
