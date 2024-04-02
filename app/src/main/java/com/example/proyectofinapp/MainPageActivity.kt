package com.example.proyectofinapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinapp.databinding.ActivityMainPageBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter


class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(18.5f, "Necesidades"))
        entries.add(PieEntry(26.7f, "Entretenimiento"))
        entries.add(PieEntry(24.0f, "Viajes"))
        entries.add(PieEntry(30.8f, "Otros"))

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.valueTextSize = 14f

        val data = PieData(dataSet)
        binding.pieChart.data = data

        binding.pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setEntryLabelColor(Color.GREEN)
            setCenterText("Mis gastos")
            setCenterTextSize(16f)
            setDrawEntryLabels(false)
            invalidate()
        }

        // Personaliza la leyenda
        val legend: Legend = binding.pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.textSize = 14f
        legend.setDrawInside(false)

        ///Line chart
        val entriesLine = ArrayList<Entry>()
        entriesLine.add(Entry(0f, 2f))
        entriesLine.add(Entry(1f, 5f))
        entriesLine.add(Entry(2f, 1f))
        entriesLine.add(Entry(3f, 3f))

        val dataSetLine = LineDataSet(entriesLine, "Ahorros")
        dataSetLine.color = Color.RED
        dataSetLine.valueTextColor = Color.BLACK

        val lineData = LineData(dataSetLine)
        binding.lineChart.data = lineData
        binding.lineChart.invalidate()

        dataSetLine.lineWidth = 2f
        dataSetLine.circleRadius = 3f
        dataSetLine.setDrawFilled(true)
        dataSetLine.fillColor = Color.BLUE

        val dates = listOf("2 Feb", "3 Feb", "4 Feb", "5 Feb", "6 Feb", "7 Feb", "8 Feb")
        binding.lineChart.xAxis.valueFormatter = DateAxisValueFormatter(dates)

        binding.lineChart.invalidate()

        //Nav bar
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
                    // Handle notifications action
                    true
                }
                R.id.user -> {
                    // Handle notifications action
                    true
                }
                R.id.homeNav -> {
                    // Handle notifications action
                    false
                }
                else -> false
            }
        }

    }
}

class DateAxisValueFormatter(private val dates: List<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return dates.getOrNull(value.toInt()) ?: value.toString()
    }
}
