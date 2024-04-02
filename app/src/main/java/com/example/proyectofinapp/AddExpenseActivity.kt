package com.example.proyectofinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinapp.databinding.ActivityAddExpenseBinding

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.location -> {
                    // Handle home action
                    true
                }
                R.id.expense -> {
                    false
                }
                R.id.search -> {
                    // Handle notifications action
                    true
                }
                R.id.user -> {
                    // Handle notifications action
                    true
                }
                R.id.homeNav -> {
                    val intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}