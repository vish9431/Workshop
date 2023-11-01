package com.example.workshop

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workshop.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LOGOUT.setOnClickListener {
            userPreferences = UserPreferences(this)
            // Call the UserPreferences to log the user out
            userPreferences.isLoggedIn = false

            // Navigate to the login screen
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish() // Close the current activityse the current activity
        }

    }

}
