package com.example.countriesinfo

import org.intellij.lang.annotations.Language

data class Country (
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val language: List<Language>
)
data class Language(
    val name: String
)
