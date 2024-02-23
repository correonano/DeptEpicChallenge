package com.dept.deptepicchallenge.domain

import com.dept.deptepicchallenge.data.DateRepository
import com.dept.deptepicchallenge.domain.model.Date
import javax.inject.Inject

class DatesDomain @Inject constructor(private var dateRepository: DateRepository) {

    suspend fun getDates(): List<Date> {
        return dateRepository.getDates().map { dto -> Date(dto.date) }
    }
}