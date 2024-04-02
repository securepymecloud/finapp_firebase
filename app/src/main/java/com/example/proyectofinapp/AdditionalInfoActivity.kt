package com.example.proyectofinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinapp.databinding.ActivityAdditionalInfoBinding

class AdditionalInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdditionalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, FinancialInfoActivity::class.java)
            startActivity(intent)
        }
    }
}