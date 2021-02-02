package com.sample.mvp.ui.login

import android.app.Application
import com.sample.mvp.ApplicationClass
import com.sample.mvp.network.INetworkApi
import com.sample.mvp.base.BasePresenter
import javax.inject.Inject

class LoginPresenterImpl(var loginViewInit: LoginView, var applicationComponent: Application) : LoginPresenter, BasePresenter<LoginView>(loginViewInit) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
    }

    override fun validateUser(userName: String, userPassword: String) {
        if (userName == "" || userPassword == "") {
            loginViewInit.onPasswordError()
        } else {
            peformLogin(userName, userPassword)
        }
    }

    override fun peformLogin(userName: String, userPassword: String) {
        if (userName == "ngocnb21" && userPassword == "Viettel@20") {
            loginViewInit.navigateToHome()
        } else {
            loginViewInit.onPasswordError()
        }
    }
}