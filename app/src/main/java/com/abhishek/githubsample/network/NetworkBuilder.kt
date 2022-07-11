package com.abhishek.githubsample.network

import com.abhishek.githubsample.constants.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// We can add the Interceptor to check the connectivity
class NetworkBuilder {

    companion object {
        var retrofitService: NetworkService? = null

        fun getInstance(): NetworkService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(AppConstants.NetworkApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(NetworkService::class.java)
            }
            return retrofitService!!
        }
    }
}