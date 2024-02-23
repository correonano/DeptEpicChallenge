package com.dept.deptepicchallenge.data.net

import com.dept.deptepicchallenge.data.model.DateDto
import com.dept.deptepicchallenge.data.model.EpicDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface NasaApi {

    @GET("all")
    suspend fun getAllDates() : List<DateDto>

    @GET("date/{date}")
    suspend fun getEpicDetail(@Path("date") date: String) : List<EpicDetail>
}