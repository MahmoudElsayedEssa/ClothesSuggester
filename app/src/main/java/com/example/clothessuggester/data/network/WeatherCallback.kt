package com.example.clothessuggester.data.network

import com.projects.whattowear.model.Interval

interface WeatherCallback {
    fun onSuccess(weatherResponse:  List<Interval>)
    fun onError(message: String)
}