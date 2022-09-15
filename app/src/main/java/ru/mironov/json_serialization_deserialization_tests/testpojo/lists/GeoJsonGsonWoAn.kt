package ru.mironov.json_serialization_deserialization_tests.testpojo.lists


data class GeoJsonGsonWoAn(

    var type: String? = null,

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

        var type: String? = null,

        var geometry: Geometry? = null
    )

    class Geometry(
        var type: String? = null,

        var coordinates: List<List<List<List<Double>?>?>?>? = null
    )
}