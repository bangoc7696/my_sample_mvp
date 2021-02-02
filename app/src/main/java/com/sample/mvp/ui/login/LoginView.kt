package com.sample.mvp.ui.login

import com.sample.mvp.ui.IView

interface LoginView: IView {

    fun navigateToHome()

    fun onBackPress()

    fun onPasswordError()
}