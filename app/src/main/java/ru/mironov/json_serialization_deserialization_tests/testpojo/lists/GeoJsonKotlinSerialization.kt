package ru.mironov.json_serialization_deserialization_tests.testpojo.lists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoJsonKotlinSerialization(
    @SerialName("type")
    var type: String? = null,

    @SerialName("features")
    var features: List<Feature?>? = null
) {
    fun toGeoJson(): GeoJson {
        val features = this.features?.map {
            GeoJson.Feature(
                type = it?.type,
                geometry = GeoJson.Geometry(
                    type = it?.geometry?.type,
                    coordinates = it?.geometry?.coordinates
                )
            )
        }

        return GeoJson(
            type = type,
            features = features
        )
    }

    @Serializable
    class Feature(
        @SerialName("type")
        var type: String? = null,

        @SerialName("geometry")
        var geometry: Geometry? = null
    )

    @Serializable
    class Geometry(
        @SerialName("type")
        var type: String? = null,

        @SerialName("coordinates")
        var coordinates: List<List<List<List<Double>?>?>?>? = null
    )
}