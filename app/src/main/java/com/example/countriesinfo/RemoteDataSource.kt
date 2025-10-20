package com.example.countriesinfo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountriesApi {
    @GET("name/{name}")
    suspend  fun getCountryByName(@Path("name") cityName: String): List<Country>
}


var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountriesApi: RestCountriesApi = retrofit.create(RestCountriesApi::class.java)