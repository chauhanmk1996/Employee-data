package com.employeeData.ui.employeeList

data class GetExperienceListResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val `data`: Data?
)

data class Data(
    val experience_data: ArrayList<ExperienceData>?
)

data class ExperienceData(
    val id: String,
    val company_name: String?,
    val experience_year: String?,
    val job_title: String?,
    val location: String?,
    val from_date: String?,
    val to_date: String?,
    val is_employed_here: String?
)