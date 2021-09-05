package com.employeeData.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.employeeData.webService.ApiInterface
import com.employeeData.webService.RetrofitUtil
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    var disposable: Disposable? = null

    /*
        Api Interface Initialization
    */
    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.apiService()
    }

    val onError = MutableLiveData<Throwable>()

    /*
        OnError
    */
    fun onError(it: Throwable?) {
        onError.value = it
    }
}