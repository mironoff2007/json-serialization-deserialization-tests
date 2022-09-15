package ru.mironov.json_serialization_deserialization_tests.testpojo.lists

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeoJsonMoshi(

    @Json(name = "type")
    var type: String? = null,

    @Json(name = "features")
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

    @JsonClass(generateAdapter = true)
    class Feature(
        @Json(name = "type")
        var type: String? = null,

        @Json(name = "geometry")
        var geometry: Geometry? = null
    )

    @JsonClass(generateAdapter = true)
    class Geometry(
        @Json(name = "type")
        var type: String? = null,

        @Json(name = "coordinates")
        var coordinates: List<List<List<List<Double>?>?>?>? = null
    )
}