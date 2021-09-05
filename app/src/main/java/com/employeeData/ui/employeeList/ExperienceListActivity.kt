package com.employeeData.ui.employeeList

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.employeeData.R
import com.employeeData.ui.addExperience.AddExperienceActivity
import com.employeeData.base.BaseActivity
import com.employeeData.ui.ExperienceViewModel
import kotlinx.android.synthetic.main.activity_experience_list.*

class ExperienceListActivity : BaseActivity() {

    private lateinit var experienceViewModel: ExperienceViewModel
    private var experienceList: ArrayList<ExperienceData> = ArrayList()
    private var experienceAdapter: ExperienceAdapter? = null

    /*
        On Create Method
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience_list)

        initView()
        initControl()
    }

    /*
        All Initialization Here
    */
    override fun initView() {
        experienceViewModel = ViewModelProvider(this).get(ExperienceViewModel::class.java)
        observer()
    }

    /*
        OnResume Function
    */
    override fun onResume() {
        super.onResume()
        showLoading()
        experienceViewModel.getEmployeeExperienceListApi()
    }

    /*
        Observer Function
    */
    private fun observer() {
        experienceViewModel.getExperienceListResponse.observe(this, {
            hideLoading()
            if (it.code == 2000 && it.success) {
                if (it.data != null && !it.data.experience_data.isNullOrEmpty()) {
                    experienceList.clear()
                    experienceList.addAll(it.data.experience_data)
                    setEmployeeExperienceAdapter()
                }
            }
        })

        experienceViewModel.onError.observe(this, {
            hideLoading()
            showError(this, rootExperienceList, it)
        })
    }

    /*
        Set Employee Experience Adapter
    */
    private fun setEmployeeExperienceAdapter() {
        if (experienceAdapter == null) {
            experienceAdapter = ExperienceAdapter(this, experienceList)
            rvExperience.adapter = experienceAdapter
        } else {
            experienceAdapter?.notifyDataSetChanged()
        }
    }

    /*
        All Controls Defines Here
    */
    override fun initControl() {
        tvAddExperience.setOnClickListener {
            startActivity(Intent(this, AddExperienceActivity::class.java))
        }
    }
}