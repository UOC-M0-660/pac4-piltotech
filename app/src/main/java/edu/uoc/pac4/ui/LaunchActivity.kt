package edu.uoc.pac4.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uoc.pac4.ui.login.LoginActivity
import edu.uoc.pac4.ui.streams.StreamsActivity
import org.koin.android.viewmodel.ext.android.viewModel


class LaunchActivity : AppCompatActivity() {

    private val viewModel: LaunchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserAvailability()
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModel.isUserAvailable.observe(this, { userAvailable ->
            if (userAvailable) {
                // User is available, open Streams Activity
                startActivity(Intent(this@LaunchActivity, StreamsActivity::class.java))
            } else {
                // User not available, request Login
                startActivity(Intent(this@LaunchActivity, LoginActivity::class.java))
            }
            finish()
        })
    }
}
