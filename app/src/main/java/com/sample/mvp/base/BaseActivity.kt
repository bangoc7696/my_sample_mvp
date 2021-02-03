package com.sample.mvp.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.sample.mvp.R
import com.sample.mvp.ui.IView

abstract class BaseActivity : Activity(), IView {

    /**
     * A dialog showing a progress indicator and an optional text message or
     * view.
     */
    protected var mProgressDialog: ProgressDialog?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        initialzeProgressDialoge()
        init(savedInstanceState)

    }

    fun initialzeProgressDialoge(){

        if(mProgressDialog==null) {
            mProgressDialog = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.setCancelable(false)
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        System.runFinalization()
        dismissProgress()
        mProgressDialog=null
    }



    @LayoutRes
    abstract fun setLayout():Int
    abstract fun init(savedInstanceState: Bundle?)
    abstract fun onStartScreen()
    abstract fun stopScreen()


    fun showProgress(msgResId: Int,
                     keyListener: DialogInterface.OnKeyListener?) {
        if (isFinishing)
            return

        if (mProgressDialog!!.isShowing) {
            return
        }

        if (msgResId != 0) {
            mProgressDialog?.setMessage(resources.getString(msgResId))
        }

        if (keyListener != null) {
            mProgressDialog?.setOnKeyListener(keyListener)

        } else {
            mProgressDialog?.setCancelable(false)
        }
        mProgressDialog?.show()
    }

    /**
     * @param isCancel
     */
    fun setCancelableProgress(isCancel: Boolean) {
        if (mProgressDialog != null) {
            mProgressDialog?.setCancelable(true)
        }
    }

    /**
     * cancel progress dialog.
     */
    fun dismissProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }


    override fun hideLoading() {
        dismissProgress()
    }

    override fun showLoading() {
        showProgress(R.string.loading, null)
    }

    override fun loadError(e: Throwable) {
        showHttpError(e)
    }

    override fun loadError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }


    /*
    Improper handling in real case
     */

    protected fun showHttpError(e: Throwable) {
        loadError(e.localizedMessage)
    }

    override fun onStop() {
        super.onStop()
        stopScreen()
    }



}