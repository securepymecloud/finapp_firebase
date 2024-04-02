package com.example.proyectofinapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener
import com.example.proyectofinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Chech if the fields are valid and not empty

        binding.loginButton.setOnClickListener{
            if(binding.emailFieldText.text?.contains("@") == false){
                binding.emailField.setError("Valor inválido")
                binding.emailField.setErrorTextColor(ColorStateList.valueOf(Color.RED))
            } else {
                if(binding.passwordFieldText.text?.isEmpty() == true){
                    binding.passwordField.setError("Está vacio")
                    binding.passwordField.setErrorTextColor(ColorStateList.valueOf(Color.RED))
                } else {
                    val intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        binding.signUp.setOnClickListener{
            val intent = Intent(this, TermsAndConditionsActivity::class.java)
            startActivity(intent)
        }

        binding.googleLogin.setOnClickListener {
            val intent = Intent(this, GoogleSignInActivity::class.java)
            startActivity(intent)
        }
    }
}