package com.sample.mvp.ui.posts

import android.app.Application
import com.sample.mvp.ApplicationClass
import com.sample.mvp.network.INetworkApi
import com.sample.mvp.base.Preseneter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class PostPresenterImpl(var postView: PostView, var applicationComponent: Application) : PostPresenter, Preseneter<PostView>(postView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
    }

    override fun getAllPosts() {
        var allPosts = mNetworkApi.getAllPosts()
        allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                postView.showAllPosts(it)
            }
    }
}