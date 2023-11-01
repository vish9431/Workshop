package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workshop.Login
import com.example.workshop.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signupSignupBtn.setOnClickListener {
            val email = binding.signupEmailTxt.text.toString()
            val password = binding.signupPasswordTxt.text.toString()

            // Perform email and password signup
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Signup successful
                        val user = auth.currentUser
                        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                        val intent= Intent(this, Login::class.java)
                        startActivity(intent)
                    } else {
                        // Signup failed
                        Toast.makeText(this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
