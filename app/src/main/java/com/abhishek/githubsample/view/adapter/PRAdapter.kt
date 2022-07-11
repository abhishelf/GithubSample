package com.abhishek.githubsample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.githubsample.R
import com.abhishek.githubsample.constants.AppConstants
import com.abhishek.githubsample.model.PullRequest
import com.abhishek.githubsample.view.holder.ClosedPRViewHolder

class PRAdapter(var pullRequests: ArrayList<PullRequest>) :
    RecyclerView.Adapter<ClosedPRViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedPRViewHolder {
        return ClosedPRViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pull_request_closed, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ClosedPRViewHolder, position: Int) {
        holder.updateView(pullRequests[position])
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    // ViewType can be extended for other pull request status also
    override fun getItemViewType(position: Int): Int {
        if (AppConstants.NetworkApi.PRStatus.CLOSED == pullRequests[position].status) {
            return AppConstants.PRViewType.CLOSED
        }

        return AppConstants.PRViewType.DEFAULT
    }

    fun updateData(pullRequest: ArrayList<PullRequest>) {
        pullRequests.clear()
        pullRequests.addAll(pullRequest)
        notifyDataSetChanged()
    }
}