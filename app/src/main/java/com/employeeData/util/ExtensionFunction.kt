package com.employeeData.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/*
    Show Message on Device Function
*/

/*
    Show Log
*/
fun showLog(message: String) {
    Log.e("###", message)
}

/*
    Show Toast
*/
fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/*
    Show Toast
*/
fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/*
    Find TimeStamp From Date
*/
fun findTimeStampFromDate(date: String): Long {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calDate: Date? = simpleDateFormat.parse(date)
    return calDate?.time!!
}

/*
    Show View
*/
fun View.show() {
    this.visibility = View.VISIBLE
}

/*
    Hide View
*/
fun View.hide() {
    this.visibility = View.GONE
}