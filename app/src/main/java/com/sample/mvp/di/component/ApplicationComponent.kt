package com.sample.mvp.di.component

import com.sample.mvp.ApplicationClass
import com.sample.mvp.di.module.AppModule
import com.sample.mvp.di.module.NetModule
import com.sample.mvp.ui.login.LoginPresenterImpl
import com.sample.mvp.ui.posts.PostPresenterImpl
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class])
interface ApplicationComponent {

    fun inject(mewApplication: ApplicationClass)
    fun inject(mLoginPresenterImpl: LoginPresenterImpl)
    fun inject(mPostPresenterImpl: PostPresenterImpl)

}