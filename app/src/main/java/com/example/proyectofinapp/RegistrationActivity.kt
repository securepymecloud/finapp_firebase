package com.example.proyectofinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinapp.databinding.ActivityRegistrationBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener{
            val email = binding.editTextEmailText.text.toString().trim()
            val password = binding.editTextPasswordText.text.toString().trim()
            if (validateForm(email, password)) {
                createAccount(email, password)
            }
        }

        binding.textViewRegisterWithGoogle.setOnClickListener {
            val intent = Intent(this, GoogleSignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    Toast.makeText(baseContext, "Registration successful.", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If registration fails, display a message to the user.
                    Toast.makeText(baseContext, "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun validateForm(email: String, password: String): Boolean {
        if (email.isEmpty() || !email.contains("@")) {
            Toast.makeText(this, "Enter a valid email address.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Navigate to another activity after registration
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        } else {
            // Optionally handle login or signup failure
        }
    }
}