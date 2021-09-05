package com.employeeData.util

import android.content.Context
import android.content.Intent
import android.view.View
import com.employeeData.R
import com.employeeData.base.BaseActivity
import com.employeeData.ui.employeeList.ExperienceListActivity
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ErrorUtil {

    private var mGsonInstance: Gson? = null

    /*
        Handel General Error
    */
    fun handlerGeneralError(context: Context?, view: View?, throwable: Throwable) {
        showLog("Error: ${throwable.message}")
        throwable.printStackTrace()

        if (context == null) return

        when (throwable) {
            is HttpException -> {
                try {
                    if (throwable.code() == 401) {
                        forceLogout(context as BaseActivity)
                    }
                    displayHttpError(view, throwable)
                } catch (exception: Exception) {
                    somethingWentWrong(view)
                    exception.printStackTrace()
                }
            }

            is ConnectException -> displayConnectionError(view)

            is SocketTimeoutException -> displaySocketTimeoutError(view)

            else -> somethingWentWrong(view)
        }
    }

    /*
        Force Logout
    */
    private fun forceLogout(activity: BaseActivity) {
        activity.startActivity(Intent(activity, ExperienceListActivity::class.java))
        activity.finishAffinity()
    }

    /*
        Display Http Error
    */
    private fun displayHttpError(view: View?, exception: HttpException) {
        try {
            mGsonInstance = Gson()
            val errorBody = mGsonInstance!!.fromJson(
                exception.response()?.errorBody()?.charStream(),
                ErrorBean::class.java
            )
            showToast(errorBody.message, view!!.context)
        } catch (e: Exception) {
            somethingWentWrong(view)
        }
    }

    /*
        Something Went Wrong
    */
    private fun somethingWentWrong(view: View?) {
        if (view == null) return
        showToast(
            view.context.getString(R.string.error_something_went_wrong_please_retry),
            view.context
        )
    }

    /*
        Display Connection Error
    */
    private fun displayConnectionError(view: View?) {
        if (view == null) return
        showToast(view.context.getString(R.string.connect_exception_error), view.context)
    }

    /*
        Display Socket Timeout Error
    */
    private fun displaySocketTimeoutError(view: View?) {
        if (view == null) return
        showToast(view.context.getString(R.string.socket_timeout_error), view.context)
    }
}

data class ErrorBean(
    val message: String
)