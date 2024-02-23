package com.dept.deptepicchallenge.data

import android.util.Log
import com.dept.deptepicchallenge.data.db.DatesDao
import com.dept.deptepicchallenge.data.model.DateDto
import com.dept.deptepicchallenge.data.model.DateMapper
import com.dept.deptepicchallenge.data.net.NasaApi
import javax.inject.Inject

class DateRepository @Inject constructor(
    private val datesDao: DatesDao,
    private val nasaApi: NasaApi
) {

    suspend fun getDates(): List<DateDto> {
        val dates = datesDao.getAll()
        if (dates.isEmpty()) {
            Log.d("dept", "from net")
            val newDates = nasaApi.getAllDates()
            datesDao.createAll(DateMapper.fromDomainList(newDates))
            return newDates
        }
        Log.d("dept", "from db")
        return DateMapper.toDomainList(dates)
    }
}