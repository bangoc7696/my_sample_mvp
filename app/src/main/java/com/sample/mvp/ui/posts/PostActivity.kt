package com.sample.mvp.ui.posts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.sample.mvp.R
import com.sample.mvp.data.PostData
import com.sample.mvp.base.BaseActivity
import com.sample.mvp.ui.adapters.PostItemAdapter
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : BaseActivity(), PostView {

    var postPresenter: PostPresenterImpl? = null

    override fun setLayout(): Int {
        return R.layout.activity_post;
    }

    override fun init(savedInstanceState: Bundle?) {
        //  postPresenter.getAllPosts()
        getPresenter()?.let {
            it.getAllPosts()
        }
    }

    fun getPresenter(): PostPresenterImpl? {
        postPresenter = PostPresenterImpl(this, application)
        return postPresenter
    }


    override fun onStartScreen() {
        //TODO
    }

    override fun stopScreen() {
        postPresenter?.let {
            postPresenter!!.unbindView()
            postPresenter = null
        }
    }

    override fun showAllPosts(postList: List<PostData>) {
        Log.d("Response", "" + postList)
        rv_post_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rv_post_list.adapter = PostItemAdapter(postList, this)
    }


}
