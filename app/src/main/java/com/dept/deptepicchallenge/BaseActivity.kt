package com.dept.deptepicchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseViewModelActivity<VM: BaseViewModel> : ComponentActivity() {

    internal lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = buildViewModel()
        viewModel.init(intent.extras)
    }

    private fun buildViewModel(): VM {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>

        return ViewModelProvider(this, defaultViewModelProviderFactory).get(viewModelClass)
    }

}