package com.dept.deptepicchallenge

import android.os.Bundle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel(){

    abstract fun init(bundle: Bundle?)

}