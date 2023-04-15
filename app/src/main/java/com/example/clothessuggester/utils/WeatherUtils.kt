//package com.example.clothessuggester.utils
//
//import android.content.Context
//import android.widget.ImageView
//import android.widget.TextView
//import com.bumptech.glide.Glide
//import com.example.clothessuggester.R
//import com.example.clothessuggester.databinding.ActivityMainBinding
//import java.text.SimpleDateFormat
//import java.util.*
//import kotlin.math.ceil
//
//object WeatherUtils {
//    fun updateWeatherInfo(
//        context: Context, weatherInfo: WeatherInfo, weatherDegree: TextView, weatherIcon: ImageView
//    ) {
//        val dayTemperature = ceil(weatherInfo.temperature.day).toInt()
//        weatherDegree.text = "${dayTemperature}°C"
//        Glide.with(context)
//            .load("https://openweathermap.org/img/wn/${weatherInfo.weather[0].icon}.png")
//            .into(weatherIcon)
//    }
//
//    fun getRandomImageForTemperature(temperature: Double, lastClothName: Int?): Int {
//        val coldImages = listOf(
//            R.drawable.winter1,
//            R.drawable.winter2,
//            R.drawable.winter3,
//            R.drawable.winter4,
//            R.drawable.winter5,
//            R.drawable.winter6,
//            R.drawable.winter7,
//            R.drawable.winter8,
//            R.drawable.winter9,
//
//            )
//        val normalImages = listOf(R.drawable.normal1, R.drawable.nurmal2)
//        val hotImages = listOf(
//            R.drawable.summer1,
//            R.drawable.summer2,
//            R.drawable.summer3,
//            R.drawable.summer4,
//            R.drawable.summer5,
//            R.drawable.summer6,
//            R.drawable.summer7,
//            R.drawable.summer8,
//            R.drawable.summer9,
//            R.drawable.summer10,
//        )
//
//        val images = when {
//            temperature <= 15 -> coldImages
//            temperature in 16.0..24.0 -> normalImages
//            else -> hotImages
//        }
//
//        val availableImages = if (lastClothName != null) {
//            images.filter { it != lastClothName }
//        } else {
//            images
//        }
//
//        return if (availableImages.isEmpty()) {
//            images.random()
//        } else {
//            availableImages.random()
//        }
//    }
//
//    fun updateClothImage(
//        binding: ActivityMainBinding, currentTemperature: Double, prefsUtil: SharedPrefsUtils
//    ) {
//        val lastClothId = prefsUtil.clothName?.toIntOrNull()
//        val newImage = getRandomImageForTemperature(currentTemperature, lastClothId)
//        binding.clothePic.setImageResource(newImage)
//        prefsUtil.clothName = newImage.toString()
//    }
//
//    fun getDayNameFromTimestamp(timestamp: Long): String {
//        val date = Date(timestamp * 1000L)
//        return SimpleDateFormat("EEEE dd, MMMM", Locale.getDefault()).format(date)
//    }
//}