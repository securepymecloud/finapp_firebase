package com.example.proyectofinapp
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener
import com.example.proyectofinapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.emailFieldText.text.toString().trim()
            val password = binding.passwordFieldText.text.toString().trim()

            if (!email.contains("@")) {
                binding.emailField.error = "Ingrese un email válido"
                binding.emailField.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            } else if (password.isEmpty()) {
                binding.passwordField.error = "La contraseña no puede estar vacía"
                binding.passwordField.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            } else {
                signIn(email, password)
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, TermsAndConditionsActivity::class.java)
            startActivity(intent)
        }

        binding.googleLogin.setOnClickListener {
            val intent = Intent(this, GoogleSignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("Login", "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("Login", "signInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}

