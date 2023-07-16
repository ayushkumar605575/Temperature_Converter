package com.example.temperatureconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.templates.TemperatureControlTemplate
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.temperatureconverter.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var fromUnit = 0
        var toUnit = 0
        val temp_list = arrayListOf<String>("Celsius","Fahrenheit","Kelvin")
        val temp_app = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,temp_list)
        binding.spinner.adapter = temp_app
        binding.spinner2.adapter = temp_app
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                fromUnit = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                fromUnit = 0
            }
        }
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                toUnit = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                toUnit = 0
            }
        }
        binding.button.setOnClickListener {
            var temp = binding.editTextText.text.toString()
            if (temp!="") {
                when (fromUnit) {
                    0 -> when (toUnit) {
                        0 -> binding.textView3.text = "Result = $temp°C"

                        1 -> binding.textView3.text = "Result = " + String.format("%.2f",((temp.toDouble() * 9 / 5) + 32)) + "°F"

                        2 -> binding.textView3.text = "Result = " + String.format("%.2f",(temp.toDouble() + 273.15)) + "K"
                    }

                    1 -> when (toUnit) {
                        0 -> binding.textView3.text = "Result = " + String.format("%.2f",((temp.toDouble() - 32) * 5 / 9)) + "°C"

                        1 -> binding.textView3.text = "Result = $temp°F"

                        2 -> binding.textView3.text = "Result = " + String.format("%.2f",((temp.toDouble() + 459.67) * 5 / 9)) + "K"
                    }

                    2 -> when (toUnit) {
                        0 -> binding.textView3.text = "Result = " + String.format("%.2f",(temp.toDouble() - 273.15)) + "°C"

                        1 -> binding.textView3.text = "Result = " + String.format("%.2f",((temp.toDouble() * 9 / 5) - 459.67)) + "°F"

                        2 -> binding.textView3.text = "Result = " + temp + "K"
                    }
                }
            }
            binding.editTextText.setText("")
        }
    }
}