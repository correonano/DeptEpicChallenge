package com.dept.deptepicchallenge.domain

import com.dept.deptepicchallenge.data.EpicDetailRepository
import com.dept.deptepicchallenge.domain.model.EpicDetails
import javax.inject.Inject

class EpicDetailDomain @Inject constructor(var epicDetailRepository: EpicDetailRepository) {

    suspend fun getEpicDetails(date: String): List<EpicDetails> {
        return epicDetailRepository.getEpicDetailList(date).map {
            EpicDetails(it.identifier, it.caption, it.image, it.date, it.day, it.lat, it.lon)
        }
    }

}