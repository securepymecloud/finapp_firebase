package com.example.proyectofinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinapp.databinding.ActivityFinancialInfoBinding

class FinancialInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinancialInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFinalize.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}