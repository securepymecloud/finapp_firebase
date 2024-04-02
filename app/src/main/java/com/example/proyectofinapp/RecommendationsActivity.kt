package com.example.proyectofinapp

import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinapp.databinding.ActivityRecommendationsBinding


class RecommendationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webview.loadUrl("https://www.google.com/maps")
        val webSettings: WebSettings = binding.webview.getSettings()
        webSettings.javaScriptEnabled = true

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.location -> {
                    // Handle home action
                    false
                }
                R.id.expense -> {
                    val intent = Intent(this, AddExpenseActivity::class.java)
                    startActivity(intent)
                    true
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
                    // Handle notifications action
                    val intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}