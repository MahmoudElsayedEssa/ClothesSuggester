package com.example.clothessuggester.data.network

import com.example.clothessuggester.data.model.Interval

interface WeatherCallback {
    fun onSuccess(weatherResponse:  List<Interval>)
    fun onError(message: String)
}