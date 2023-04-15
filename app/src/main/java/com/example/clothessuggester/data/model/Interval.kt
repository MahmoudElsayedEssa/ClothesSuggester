package com.example.clothessuggester.data.model

import androidx.annotation.DrawableRes

data class Interval(
    val startTime: String,
    val values: Temperature,
    val weatherType: DayWeatherType,
    @DrawableRes val weatherImageId: Int,
    @DrawableRes val clothesImageId: Int,
)
