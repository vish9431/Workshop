package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UserPreferences
        userPreferences = UserPreferences(this)

        // Check if the user is already logged in
        if (userPreferences.isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Authentication
        auth = FirebaseAuth.getInstance()

        //Create New User
        binding.createUser.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            val email = binding.etName.text.toString()
            val password = binding.etPass.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Sign in with email and password
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Login successful
                            val user = auth.currentUser
                            userPreferences.isLoggedIn = true
                            // Navigate to the next screen
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            // Login failed
                            Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()

                        }
                    }
            } else {
                // Handle empty email or password fields
                Toast.makeText(this, "Please fill in both email and password fields.", Toast.LENGTH_SHORT).show()

            }
        }

    }
}
