package com.dept.deptepicchallenge.data

import com.dept.deptepicchallenge.data.db.EpicDetailDao
import com.dept.deptepicchallenge.data.model.EpicDetailLocal
import com.dept.deptepicchallenge.data.model.EpicDetailMapper
import com.dept.deptepicchallenge.data.net.NasaApi
import javax.inject.Inject

class EpicDetailRepository @Inject constructor(
    private val epicDetailDao: EpicDetailDao,
    private val nasaApi: NasaApi
) {

    suspend fun addEpicDetails(details: List<EpicDetailLocal>) {
        epicDetailDao.createAll(details)
    }

    suspend fun getEpicDetailList(date: String): List<EpicDetailLocal> {
        var detailsLocal = epicDetailDao.getDetails(date)
        if (detailsLocal != null && detailsLocal.size > 0)
            return detailsLocal
        else {
            val details = nasaApi.getEpicDetail(date)
            detailsLocal = EpicDetailMapper.fromDomainList(details)
            addEpicDetails(detailsLocal)
            return detailsLocal
        }

    }

}