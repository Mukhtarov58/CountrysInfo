package com.example.countriesinfo

import  android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.example.countriesinfo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val countries: List<Country> = restCountriesApi.getCountryByName(countryName)
                    val country: Country = countries[0]

                    binding.countryNameTextView.text = country.name
                    binding.capitalNameTaxtView.text = country.capital
                    binding.populationTextView.text = formatNumber(country.population)
                    binding.areaTextView.text = formatNumber(country.area)
                    binding.langvidgesTextView.text = languagesToString(country.languages)
                    loadSvg(binding.imageView, country.flags.svg)

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                }catch (e: Exception){
                    binding.statusTextView.text = "Страна не найдена"
                    binding.statusImageView.setImageResource(R.drawable.baseline_adb_24)
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }
        }
    }
}
