package com.abhishek.githubsample.view.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.githubsample.R
import com.abhishek.githubsample.model.PullRequest
import com.abhishek.githubsample.utils.DateUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ClosedPRViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val username: TextView = view.findViewById(R.id.tvTitle)
    private val prTitle: TextView = view.findViewById(R.id.tvDescription)
    private val closedAt: TextView = view.findViewById(R.id.tvSubDescription)
    private val userAvatar: ImageView = view.findViewById(R.id.ivItemIcon)

    private val requestOptions: RequestOptions =
        RequestOptions().placeholder(R.drawable.placeholder_user_avatar).circleCrop()

    fun updateView(pullRequest: PullRequest) {
        username.text = pullRequest.user?.userName
        prTitle.text = pullRequest.title
        closedAt.text = DateUtil.mergeClosedCreateDate(pullRequest.closedAt, pullRequest.createdAt)

        Glide.with(itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(pullRequest.user?.avatarUrl)
            .into(userAvatar)
    }
}