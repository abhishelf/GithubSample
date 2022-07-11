package com.abhishek.githubsample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.githubsample.constants.AppConstants
import com.abhishek.githubsample.model.PullRequest
import com.abhishek.githubsample.network.NetworkBuilder
import com.abhishek.githubsample.repository.NetworkRepository
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val closedPRList = MutableLiveData<ArrayList<PullRequest>>()
    val dataLoadingStatus = MutableLiveData<Int>()

    private val networkRepository: NetworkRepository =
        NetworkRepository(NetworkBuilder.getInstance())

    private var networkJob: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        dataLoadingStatus.postValue(AppConstants.NetworkLoadingStatus.FAILED)
    }

    fun refreshPRList(userId: String, repoName: String, status: String) {
        networkJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            dataLoadingStatus.postValue(AppConstants.NetworkLoadingStatus.LOADING)
            val response = networkRepository.getClosedPR(userId, repoName, status)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    closedPRList.postValue(response.body())
                    dataLoadingStatus.postValue(AppConstants.NetworkLoadingStatus.SUCCEEDED)
                } else {
                    dataLoadingStatus.postValue(AppConstants.NetworkLoadingStatus.FAILED)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkJob?.cancel()
    }
}