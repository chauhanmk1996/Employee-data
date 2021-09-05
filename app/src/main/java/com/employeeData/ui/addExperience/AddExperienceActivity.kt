package com.employeeData.ui.addExperience

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.employeeData.R
import com.employeeData.base.BaseActivity
import com.employeeData.ui.ExperienceViewModel
import com.employeeData.util.*
import kotlinx.android.synthetic.main.activity_add_experience.*

class AddExperienceActivity : BaseActivity() {

    private lateinit var experienceViewModel: ExperienceViewModel
    private var addExperienceList: ArrayList<ExperienceData> = ArrayList()
    private var addExperienceAdapter: AddExperienceAdapter? = null

    /*
        On Create Method
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_experience)

        initView()
        initControl()
    }

    /*
        All Initialization Here
    */
    override fun initView() {
        experienceViewModel = ViewModelProvider(this).get(ExperienceViewModel::class.java)
        observer()
        addExperienceList.add(ExperienceData("", "", "", "", "", "NO"))
        setAddExperienceAdapter()
    }

    /*
        Set Add Experience Adapter
    */
    private fun setAddExperienceAdapter() {
        if (addExperienceAdapter == null) {
            addExperienceAdapter =
                AddExperienceAdapter(this, addExperienceList, object : AddExperienceListener {
                    override fun closeclick(position: Int) {
                        addExperienceList.removeAt(position)
                        setAddExperienceAdapter()
                    }

                    override fun fromDateSelect(position: Int) {
                        selectFromDate(object : DateListener {
                            override fun dateSelect(date: String) {
                                addExperienceList[position].from_date = date
                                setAddExperienceAdapter()
                            }
                        })
                    }

                    override fun toDateSelect(position: Int, fromDate: String) {
                        val fromTimeStamp = findTimeStampFromDate(fromDate)
                        selectToDate(fromTimeStamp, object : DateListener {
                            override fun dateSelect(date: String) {
                                addExperienceList[position].end_date = date
                                setAddExperienceAdapter()
                            }
                        })
                    }

                    override fun currentEmployedChange(currentlyEmployed: String, position: Int) {
                        if (currentlyEmployed == "YES") {
                            addExperienceList[position].end_date = ""
                        }
                        addExperienceList[position].currently_employed_here = currentlyEmployed
                        setAddExperienceAdapter()
                    }
                })
            rvAddExperience.adapter = addExperienceAdapter
        } else {
            addExperienceAdapter?.notifyDataSetChanged()
        }
    }

    /*
        Observer Function
    */
    private fun observer() {
        experienceViewModel.addExperienceResponse.observe(this, {
            hideLoading()
            showToast(getString(R.string.experience_added_successfully))
            onBackPressed()
        })

        experienceViewModel.onError.observe(this, {
            hideLoading()
            showError(this, rootAddExperience, it)
        })
    }

    /*
        All Controls Defines Here
    */
    override fun initControl() {
        ivBack.setOnClickListener {
            onBackPressed()
        }

        tvAddExperience.setOnClickListener {
            if (isInputValid()) {
                addExperienceList.add(ExperienceData("", "", "", "", "", "NO"))
                setAddExperienceAdapter()
            }
        }

        btnSave.setOnClickListener {
            if (isInputValid()) {
                addExperienceListApi()
            }
        }
    }

    /*
        Input Validation Function
    */
    private fun isInputValid(): Boolean {
        for (i in 0 until addExperienceList.size) {
            val experience = addExperienceList[i]
            if (experience.job_title == "") {
                showToast(getString(R.string.please_enter_job_title))
                return false
            }

            if (experience.company_name == "") {
                showToast(getString(R.string.please_enter_company_name))
                return false
            }

            if (experience.location == "") {
                showToast(getString(R.string.please_enter_location))
                return false
            }

            if (experience.from_date == "") {
                showToast(getString(R.string.please_select_from_date))
                return false
            }

            if (experience.currently_employed_here == "NO") {
                if (experience.end_date == "") {
                    showToast(getString(R.string.please_select_to_date))
                    return false
                }
            }
        }
        setAddExperienceAdapter()
        return true
    }

    /*
        Add Experience List Api
    */
    private fun addExperienceListApi() {
        val addExperienceRequest = AddExperienceRequest(
            experience_data = addExperienceList
        )
        showLoading()
        experienceViewModel.addEmployeeExperienceListApi(
            addExperienceRequest = addExperienceRequest
        )
    }

    /*
        Job Title Change in Add Experience List
    */
    fun jobTitleChange(jobTitle: String, position: Int) {
        addExperienceList[position].job_title = jobTitle
    }

    /*
        Company Name Change in Add Experience List
    */
    fun companyNameChange(companyName: String, position: Int) {
        addExperienceList[position].company_name = companyName
    }

    /*
        Location Change in Add Experience List
    */
    fun locationChange(location: String, position: Int) {
        addExperienceList[position].location = location
    }
}