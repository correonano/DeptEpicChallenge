package com.dept.deptepicchallenge.data.model

import com.google.gson.annotations.SerializedName

data class EpicDetail(
    val identifier: String,
    val caption: String,
    val image: String,
    val version: String,
    val date: String,
    @SerializedName("centroid_coordinates")
    val coordinates: Coordinates,
)

data class Coordinates(
    val lat: Float,
    val lon: Float,
)
