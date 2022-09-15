package ru.mironov.json_serialization_deserialization_tests.testpojo.lists


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeoJsonGson(
    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("features")
    @Expose
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


    class Feature(
        @SerializedName("type")
        @Expose
        var type: String? = null,

        @SerializedName("geometry")
        @Expose
        var geometry: Geometry? = null
    )

    class Geometry(
        @SerializedName("type")
        @Expose
        var type: String? = null,

        @SerializedName("coordinates")
        @Expose
        var coordinates: List<List<List<List<Double>?>?>?>? = null
    )
}