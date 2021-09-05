package com.employeeData.ui.addExperience

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.employeeData.R
import com.employeeData.util.hide
import com.employeeData.util.show
import kotlinx.android.synthetic.main.list_item_add_experience.view.*
import kotlin.collections.ArrayList

class AddExperienceAdapter(
    private val context: Context,
    private val addExperienceList: ArrayList<ExperienceData>,
    private val addExperienceListener: AddExperienceListener
) :
    RecyclerView.Adapter<AddExperienceAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /*
        Inflate view for recycler
    */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.list_item_add_experience, p0, false)
        return MyViewHolder(
            view
        )
    }

    /*
        Return size
    */
    override fun getItemCount(): Int {
        return addExperienceList.size
    }

    /*
        Bind View Holder
    */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply {
            val experience = addExperienceList[position]

            if (position == 0) {
                ivClose.hide()
            } else {
                ivClose.show()
            }

            ivClose.setOnClickListener {
                addExperienceListener.closeclick(holder.adapterPosition)
            }

            if (experience.job_title != "") {
                etJobTitle.setText(experience.job_title)
            }

            etJobTitle.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0 != null && p0.toString() != "") {
                        (context as AddExperienceActivity).jobTitleChange(
                            p0.toString(),
                            holder.adapterPosition
                        )
                    }
                }
            })

            if (experience.company_name != "") {
                etCompanyName.setText(experience.company_name)
            }

            etCompanyName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0 != null && p0.toString() != "") {
                        (context as AddExperienceActivity).companyNameChange(
                            p0.toString(),
                            holder.adapterPosition
                        )
                    }
                }
            })

            if (experience.location != "") {
                etLocation.setText(experience.location)
            }

            etLocation.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0 != null && p0.toString() != "") {
                        (context as AddExperienceActivity).locationChange(
                            p0.toString(),
                            holder.adapterPosition
                        )
                    }
                }
            })

            etFrom.setText(addExperienceList[position].from_date)

            etFrom.setOnClickListener {
                addExperienceListener.fromDateSelect(holder.adapterPosition)
            }

            etTo.setText(addExperienceList[position].end_date)

            etTo.setOnClickListener {
                addExperienceListener.toDateSelect(
                    holder.adapterPosition,
                    addExperienceList[position].from_date
                )
            }

            if (addExperienceList[position].currently_employed_here == "YES") {
                ivCurrentlyEmployed.setImageResource(R.drawable.on)
            } else {
                ivCurrentlyEmployed.setImageResource(R.drawable.off)
            }

            ivCurrentlyEmployed.setOnClickListener {
                if (addExperienceList[position].currently_employed_here == "YES") {
                    addExperienceListener.currentEmployedChange("NO", holder.adapterPosition)
                } else {
                    addExperienceListener.currentEmployedChange("YES", holder.adapterPosition)
                }
            }
        }
    }
}

interface AddExperienceListener {
    fun closeclick(position: Int)
    fun fromDateSelect(position: Int)
    fun toDateSelect(position: Int, fromDate: String)
    fun currentEmployedChange(currentlyEmployed: String, position: Int)
}