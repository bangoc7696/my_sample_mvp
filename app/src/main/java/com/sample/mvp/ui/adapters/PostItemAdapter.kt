package com.sample.mvp.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.mvp.R
import com.sample.mvp.data.PostData
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostItemAdapter(val postItems: List<PostData>, val context: Context) : RecyclerView.Adapter<PostItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_list_item, parent, false))

    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder?.itemView.tv_post_title.text = postItems.get(position).title
            holder?.itemView.tv_post_body.text = postItems.get(position).body
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}