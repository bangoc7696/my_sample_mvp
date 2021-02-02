package com.sample.mvp.ui.login

interface LoginPresenter {

    fun peformLogin(userName: String, userPassword: String)

    fun validateUser(userName: String, userPassword: String)
}