package ru.mironov.json_serialization_deserialization_tests.testpojo.geojson


import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GeoJackson(
    @field:JsonProperty("type")
    @param:JsonProperty("type")
    var type: String? = null,

    @field:JsonProperty("features")
    @param:JsonProperty("features")
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Feature @JsonCreator constructor(
        @field:JsonProperty("type")
        @param:JsonProperty("type")
        var type: String? = null,

        @field:JsonProperty("geometry")
        @param:JsonProperty("geometry")
        var geometry: Geometry? = null
    )

    class Geometry @JsonCreator constructor(
        @field:JsonProperty("type")
        @param:JsonProperty("type")
        var type: String? = null,

        @field:JsonProperty("coordinates")
        @param:JsonProperty("coordinates")
        var coordinates: List<List<List<List<Double>?>?>?>? = null
    )
}