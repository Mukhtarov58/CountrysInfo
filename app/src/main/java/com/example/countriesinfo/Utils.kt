package com.example.countriesinfo

import android.graphics.Insets
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
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

suspend fun loadSvg(imageView: ImageView, url: String) {
    val context = imageView.context

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (url.lowercase(Locale.ITALY).endsWith("svg")) {
                add(SvgDecoder.Factory())
            }
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(url)
        .target(imageView)
        .build()

    // В Coil 2.x+ execute является suspend функцией
    imageLoader.execute(request)
}

fun loadCountryFlag(imageView: ImageView, flags: Flags) {
    val context = imageView.context

    // Создаем ImageLoader с поддержкой SVG
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    // Загружаем SVG флаг
    imageView.load(flags.svg, imageLoader) {
        // Добавляем обработчики для отладки
        listener(
            onStart = { println("Loading SVG: ${flags.svg}") },
            onSuccess = { _, _ -> println("SVG loaded successfully") },
            onError = { _, result ->
                println("SVG error: ${result.throwable.message}")
                // Если SVG не загрузился, пробуем PNG
                val pngUrl = flags.svg.replace(".svg", ".png")
                imageView.load(pngUrl)
            }
        )
    }
}
