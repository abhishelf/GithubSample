package com.abhishek.githubsample.constants

interface AppConstants {

    object NetworkApi {
        const val BASE_URL: String = "https://api.github.com/"

        object PRStatus {
            const val CLOSED = "closed"
            const val OPEN = "open"
        }
    }

    object NetworkLoadingStatus {
        const val LOADING: Int = 1
        const val SUCCEEDED: Int = 2
        const val IDLE: Int = 0
        const val FAILED: Int = -1
    }

    object PRViewType {
        const val CLOSED = 2
        const val OPEN = 1
        const val DEFAULT = 0
    }

    object DummyData {
        const val USER_NAME: String = "google"
        const val REPO_NAME: String = "ExoPlayer"
        const val PR_STATUS: String = NetworkApi.PRStatus.CLOSED
    }
}