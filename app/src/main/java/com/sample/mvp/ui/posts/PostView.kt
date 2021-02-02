package com.sample.mvp.ui.posts

import com.sample.mvp.data.PostData
import com.sample.mvp.ui.IView

interface PostView: IView {

    fun showAllPosts(postList: List<PostData>)
}