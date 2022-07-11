package com.abhishek.githubsample.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhishek.githubsample.R
import com.abhishek.githubsample.view.fragment.PRFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.pull_request)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentFrame, PRFragment(), "PRFragment")
            .commit()
    }
}