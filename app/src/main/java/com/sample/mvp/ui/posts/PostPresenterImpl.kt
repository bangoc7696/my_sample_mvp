package com.sample.mvp.ui.posts

import android.app.Application
import com.sample.mvp.ApplicationClass
import com.sample.mvp.network.INetworkApi
import com.sample.mvp.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class PostPresenterImpl(var postView: PostView, var applicationComponent: Application) : PostPresenter, BasePresenter<PostView>(postView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
    }

    override fun getAllPosts() {
        try {
            postView.showLoading()
            var allPosts = mNetworkApi.getAllPosts()
            allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    postView.hideLoading()
                    postView.showAllPosts(it)
                }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}