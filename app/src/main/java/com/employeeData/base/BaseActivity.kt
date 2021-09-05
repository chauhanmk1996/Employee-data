package com.employeeData.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.employeeData.R
import com.employeeData.constant.Constant.Companion.BACK_PRESS_TIME_INTERVAL
import com.employeeData.util.ErrorUtil
import com.employeeData.util.ProgressDialogUtil

abstract class BaseActivity : AppCompatActivity() {

    /*
        On Create
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun initView()
    abstract fun initControl()

    private var doubleBackToExitPressedOnce: Boolean = false

    /*
        On BackPressed
    */
    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> super.onBackPressed()
            isTaskRoot -> {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }
                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, getString(R.string.back_press_exit_line), Toast.LENGTH_SHORT)
                    .show()
                Handler(Looper.myLooper()!!).postDelayed(
                    { doubleBackToExitPressedOnce = false },
                    BACK_PRESS_TIME_INTERVAL
                )
            }
            else -> super.onBackPressed()
        }
    }

    /*
        Hide Progress Bar
    */
    fun hideLoading() {
        ProgressDialogUtil.getInstance().hideProgress()
    }

    /*
        Show Progress Bar
    */
    fun showLoading() {
        hideLoading()
        ProgressDialogUtil.getInstance().showProgress(this, false)
    }

    /*
        Show Error
    */
    fun showError(context: Context?, view: View?, throwable: Throwable) {
        ErrorUtil.handlerGeneralError(context, view, throwable)
    }

    /*
        Show Log
    */
    fun showLog(message: String) {
        Log.e("###", message)
    }
}