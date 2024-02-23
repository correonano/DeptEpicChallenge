package com.dept.deptepicchallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
class EpicDetailLocal(
    @PrimaryKey
    val identifier: String,
    val caption: String,
    val image: String,
    val versionImage: String,
    val date: String,
    val day: String,
    val lat: Float,
    val lon: Float,)