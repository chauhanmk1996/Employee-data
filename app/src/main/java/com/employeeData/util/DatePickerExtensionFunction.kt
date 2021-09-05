package com.employeeData.util

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/*
    Select From Date Picker Dialog
*/
fun Activity.selectFromDate(dateListener: DateListener) {
    val calendar = Calendar.getInstance()
    val year1 = calendar.get(Calendar.YEAR)
    val month1 = calendar.get(Calendar.MONTH)
    val day1 = calendar.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(
        this, { view, year, monthOfYear, dayOfMonth ->
            val date = String.format("%02d", dayOfMonth)
            val month = String.format("%02d", (monthOfYear + 1))
            val fullDate = "$year-$month-$date"
            dateListener.dateSelect(fullDate)
        },
        year1,
        month1,
        day1
    )
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    datePickerDialog.show()
}

/*
    Select To Date Picker Dialog
*/
fun Activity.selectToDate(fromTimeStamp: Long, dateListener: DateListener) {
    val calendar = Calendar.getInstance()
    val year1 = calendar.get(Calendar.YEAR)
    val month1 = calendar.get(Calendar.MONTH)
    val day1 = calendar.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(
        this, { view, year, monthOfYear, dayOfMonth ->
            val date = String.format("%02d", dayOfMonth)
            val month = String.format("%02d", (monthOfYear + 1))
            val fullDate = "$year-$month-$date"
            dateListener.dateSelect(fullDate)
        },
        year1,
        month1,
        day1
    )
    datePickerDialog.datePicker.minDate = fromTimeStamp
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    datePickerDialog.show()
}

/*
    Date Picker Dialog Listener
*/
interface DateListener {
    fun dateSelect(date: String)
}