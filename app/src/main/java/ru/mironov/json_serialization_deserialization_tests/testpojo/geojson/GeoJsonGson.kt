package ru.mironov.json_serialization_deserialization_tests.testpojo.geojson


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeoJsonGson(

    @SerializedName("features")
    @Expose
    private val _features: List<Feature>? = null

) : BaseGeoJson() {

    val features by lazy { _features ?: emptyList() }

    fun toGeoJson(): GeoJson {
        val features = this.features.map {
            GeoJson.Feature(
                type = it.type,
                geometry = GeoJson.Geometry(
                    type = it.geometry.type,
                    coordinates = it.geometry.coordinates
                )
            )
        }

        return GeoJson(
            type = type,
            features = features
        )
    }

    class Feature(

        @SerializedName("geometry")
        @Expose
        private val _geometry: Geometry? = null

    ) : BaseGeoJson() {
        val geometry by lazy { _geometry ?: Geometry() }
    }

    class Geometry(
        @SerializedName("coordinates")
        @Expose
        private val _coordinates: List<List<List<List<Double>?>?>?>? = null
    ) : BaseGeoJson() {
        val coordinates by lazy { _coordinates ?: emptyList() }
    }
}

open class BaseGeoJson(
    @SerializedName("type")
    @Expose
    val _type: String? = null,
) {
    val type by lazy { _type ?: "" }
}