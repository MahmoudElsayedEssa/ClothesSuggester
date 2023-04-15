package com.example.clothessuggester.ui

import com.example.clothessuggester.data.model.Interval

interface MainView {
    fun setupIntervals(weatherResponse: List<Interval>)
    fun showError(error: String)
}
