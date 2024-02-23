package com.dept.deptepicchallenge.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dept.deptepicchallenge.BaseViewModel
import com.dept.deptepicchallenge.domain.DatesDomain
import com.dept.deptepicchallenge.domain.EpicDetailDomain
import com.dept.deptepicchallenge.domain.model.Date
import com.dept.deptepicchallenge.domain.model.EpicDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val datesDomain: DatesDomain,
    private val epicDetailDomain: EpicDetailDomain
) : BaseViewModel() {

    private val _dateList = MutableLiveData<List<Date>>()
    val dateList: LiveData<List<Date>>
        get() = _dateList

    private val _imageList = MutableLiveData<List<EpicDetails>>()
    val imageList: LiveData<List<EpicDetails>>
        get() = _imageList


    override fun init(bundle: Bundle?) {
        fetchDateList()
    }

    private val coroutineExceptionHanlder = CoroutineExceptionHandler{ _, throwable ->
        Log.d("viewmodel", "error")
    }

    @VisibleForTesting
    fun fetchDateList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            val dates = datesDomain.getDates()
            withContext(Dispatchers.Main) {
                _dateList.value = dates
            }
        }
    }

    @VisibleForTesting
    fun fetchImageList(date: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            val images = epicDetailDomain.getEpicDetails(date)
            withContext(Dispatchers.Main) {
                _imageList.value = images
            }
        }
    }

}
