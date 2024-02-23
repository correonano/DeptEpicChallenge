package com.dept.deptepicchallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dates")
class DateLocal(
    @PrimaryKey
    val date: String,)