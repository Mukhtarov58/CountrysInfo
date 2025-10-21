package com.example.countriesinfo

import java.text.NumberFormat
import java.util.Locale

fun languagesToString(languages: List<Language>): String {
//    var result = ""
//    for ((index , language) in languages.withIndex())
//        if (index != languages.lastIndex)
//            result += "${language.name}, "
//        else
//            result += language.name
//    return result
    return languages.joinToString{it.name}
}
fun formatNumber(number: Long): String{
    val string = NumberFormat.getNumberInstance(Locale.ITALY).format( number)
    return string
}