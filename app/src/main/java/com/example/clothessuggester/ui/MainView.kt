package com.example.clothessuggester.ui

import com.projects.whattowear.model.Interval

interface MainView {
    fun setupIntervals(weatherResponse: List<Interval>)
    fun showError(error: String)
}
