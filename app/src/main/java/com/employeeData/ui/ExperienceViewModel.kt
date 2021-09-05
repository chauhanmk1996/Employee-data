package com.employeeData.ui

import androidx.lifecycle.MutableLiveData
import com.employeeData.base.BaseViewModel
import com.employeeData.ui.addExperience.AddExperienceRequest
import com.employeeData.ui.addExperience.AddExperienceResponse
import com.employeeData.ui.employeeList.GetExperienceListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExperienceViewModel : BaseViewModel() {

    val getExperienceListResponse = MutableLiveData<GetExperienceListResponse>()
    val addExperienceResponse = MutableLiveData<AddExperienceResponse>()

    /*
        Get Employee Experience List Api
    */
    fun getEmployeeExperienceListApi() {
        disposable = apiInterface.getEmployeeExperienceListApi(
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onGetNewBrandListSuccess(it) }, { onError(it) })
    }

    /*
        Get Employee Experience List Success
    */
    private fun onGetNewBrandListSuccess(it: GetExperienceListResponse?) {
        getExperienceListResponse.value = it
    }

    /*
        Add Experience List Api
    */
    fun addEmployeeExperienceListApi(addExperienceRequest: AddExperienceRequest) {
        disposable = apiInterface.addEmployeeExperienceListApi(
            addExperienceRequest = addExperienceRequest
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onAddEmployeeExperienceSuccess(it) }, { onError(it) })
    }

    /*
        Add Employee Experience List Success
    */
    private fun onAddEmployeeExperienceSuccess(it: AddExperienceResponse?) {
        addExperienceResponse.value = it
    }
}