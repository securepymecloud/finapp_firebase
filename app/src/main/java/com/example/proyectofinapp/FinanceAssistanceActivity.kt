package com.example.proyectofinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinapp.databinding.ActivityFinanceAssistanceBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class FinanceAssistanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinanceAssistanceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinanceAssistanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helpers:ArrayList<Helpers> = loadHelpers()

        binding.helpersList.adapter = HelperAdapter(this, helpers)

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.location -> {
                    val intent = Intent(this, RecommendationsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.expense -> {
                    val intent = Intent(this, AddExpenseActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.search -> {
                    false
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

    private fun loadHelpers(): ArrayList<Helpers> {
        val helpers = ArrayList<Helpers>()

        val jsonString = loadJSONfromAssets("helpers.json") ?: return helpers

        val jsonObject = JSONObject(jsonString)

        val helpersList = jsonObject.getJSONArray("Helpers")

        for (i in 0 until helpersList.length()) {
            val helper = helpersList.getJSONObject(i)
            helpers.add(
                Helpers(
                    helper.getString("name"),
                    helper.getString("description"),
                    helper.getString("numberRating")
                )
            )
        }

        return helpers
    }


    private fun loadJSONfromAssets(name: String) : String{
        return try {
            val inputStream = assets.open(name)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            String(buffer, charset("UTF-8"))
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            ""
        }
    }

    private fun loadJSONfromString(jsonString: String) : JSONObject {
        return try {
            JSONObject(jsonString)
        } catch (ex: org.json.JSONException){
            ex.printStackTrace()
            JSONObject()
        }
    }
}