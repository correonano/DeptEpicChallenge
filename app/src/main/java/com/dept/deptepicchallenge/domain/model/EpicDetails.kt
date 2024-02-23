package com.dept.deptepicchallenge.domain.model

data class EpicDetails(
    val identifier: String,
    val caption: String,
    val image: String,
    val date: String,
    val day: String,
    val lat: Float,
    val lon: Float
)