package com.employeeData.webService

import com.employeeData.constant.NetworkConstants
import com.employeeData.ui.addExperience.AddExperienceRequest
import com.employeeData.ui.addExperience.AddExperienceResponse
import com.employeeData.ui.employeeList.GetExperienceListResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    /*
        Get Employee List Api
    */
    @GET(NetworkConstants.EMPLOYEE_LIST)
    fun getEmployeeExperienceListApi(
    ): Observable<GetExperienceListResponse>

    /*
        Add Experience Api
    */
    @POST(NetworkConstants.ADD_EXPERIENCE)
    fun addEmployeeExperienceListApi(
        @Body addExperienceRequest: AddExperienceRequest
    ): Observable<AddExperienceResponse>
}