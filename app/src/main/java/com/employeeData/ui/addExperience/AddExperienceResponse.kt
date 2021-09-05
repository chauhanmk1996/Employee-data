package com.employeeData.ui.addExperience

data class AddExperienceResponse(
    val code: Int,
    val message: String,
    val success: Boolean
)

data class AddExperienceRequest(
    val experience_data: ArrayList<ExperienceData>
)

data class ExperienceData(
    var job_title: String,
    var company_name: String,
    var location: String,
    var from_date: String,
    var end_date: String,
    var currently_employed_here: String
)