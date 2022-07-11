package com.abhishek.githubsample.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.githubsample.R
import com.abhishek.githubsample.constants.AppConstants
import com.abhishek.githubsample.view.adapter.PRAdapter
import com.abhishek.githubsample.viewmodel.MainViewModel

class PRFragment : Fragment() {

    private val prListAdapter: PRAdapter = PRAdapter(arrayListOf())

    private lateinit var prListRv: RecyclerView
    private lateinit var errorView: FrameLayout
    private lateinit var loadingView: FrameLayout
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_p_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorView = view.findViewById(R.id.errorView)
        loadingView = view.findViewById(R.id.loadingView)
        prListRv = view.findViewById(R.id.prListsView)

        prListRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = prListAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel?.refreshPRList(
            AppConstants.DummyData.USER_NAME,
            AppConstants.DummyData.REPO_NAME,
            AppConstants.DummyData.PR_STATUS
        )

        startObserver()
    }

    private fun startObserver() {
        mainViewModel.closedPRList.observe(this) {
            Log.d("LoadingStatus", "Pull Request Size: ${it.size}")
            prListAdapter.updateData(it)
        }

        mainViewModel.dataLoadingStatus.observe(this) {
            Log.d("LoadingStatus", "Status : $it")
            changeView(it)
        }
    }

    private fun changeView(loadingStatus: Int) {
        when (loadingStatus) {
            AppConstants.NetworkLoadingStatus.FAILED -> {
                errorView.visibility = View.VISIBLE
                prListRv.visibility = View.GONE
                loadingView.visibility = View.GONE
            }
            AppConstants.NetworkLoadingStatus.SUCCEEDED -> {
                errorView.visibility = View.GONE
                prListRv.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
            }
            else -> {
                errorView.visibility = View.GONE
                prListRv.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
            }
        }
    }
}