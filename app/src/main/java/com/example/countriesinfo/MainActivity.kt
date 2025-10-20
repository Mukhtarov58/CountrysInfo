package com.example.countriesinfo

import  android.os.Bundle
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
                val countries: List<Country> = restCountriesApi.getCountryByName(countryName)
                val country: Country = countries[0]

                binding.countryNameTextView.text = country.name
                binding.capitalNameTaxtView.text = country.capital
                binding.populationTextView.text = country.population.toString()
                binding.areaTextView.text = country.area.toString()
                binding.langvidgesTextView.text = country.languages.toString()
            }
        }
    }
}
