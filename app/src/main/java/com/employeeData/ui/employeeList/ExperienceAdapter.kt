package com.employeeData.ui.employeeList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.employeeData.R
import kotlinx.android.synthetic.main.list_item_experience.view.*
import kotlin.collections.ArrayList

class ExperienceAdapter(
    private val context: Context,
    private val experienceList: ArrayList<ExperienceData>
) :
    RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /*
        Inflate view for recycler
    */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.list_item_experience, p0, false)
        return MyViewHolder(
            view
        )
    }

    /*
        Return size
    */
    override fun getItemCount(): Int {
        return experienceList.size
    }

    /*
        Bind View Holder
    */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply {
            val experience = experienceList[position]

            val companyName: String? = experience.company_name
            if (companyName != null) {
                tvCompanyName.text = companyName
            }

            val experienceYear: String? = experience.experience_year
            if (experienceYear != null) {
                val experienceText = "$experienceYear Year"
                tvExperience.text = experienceText
            }

            val jobTitle: String? = experience.job_title
            if (jobTitle != null) {
                tvJobTitle.text = jobTitle
            }

            val location: String? = experience.location
            if (location != null) {
                tvLocation.text = location
            }

            val fromDate: String? = experience.from_date
            val toDate: String? = experience.to_date
            val currentlyWorking: String? = experience.is_employed_here
            var time = ""
            if (fromDate != null) {
                if (currentlyWorking != null && currentlyWorking == "YES") {
                    time = "$fromDate - present"
                } else if (currentlyWorking == "NO") {
                    if (toDate != null) {
                        time = "$fromDate - $toDate"
                    }
                }
                tvTime.text = time
            }
        }
    }
}