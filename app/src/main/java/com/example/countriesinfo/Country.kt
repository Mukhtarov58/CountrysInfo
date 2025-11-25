package com.example.countriesinfo


data class Country (
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flags: Flags
)
data class Language(
    val name: String
)
data class Flags(
    val svg: String,
    val png: String? = null
)
