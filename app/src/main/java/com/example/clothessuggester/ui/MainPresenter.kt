package com.example.clothessuggester.ui

import android.content.SharedPreferences
import com.example.clothessuggester.data.local.PrefsUtil.initPrefs
import com.example.clothessuggester.data.local.PrefsUtil.startTimeAndImageId
import com.example.clothessuggester.data.model.Interval
import com.example.clothessuggester.data.network.TomorrowApiClient
import com.example.clothessuggester.data.network.WeatherCallback

class MainPresenter(
    private val tomorrowApiClient: TomorrowApiClient,
    private val sharedPref: SharedPreferences
) : WeatherCallback {

    private var meanView: MainView? = null
    fun attachView(view: MainView) {
        meanView = view
    }

    fun detachView() {
        meanView = null
    }


    fun initScreen() {
        initPrefs(sharedPref)
        tomorrowApiClient.getIntervals(callback = this, previousClothesImage = startTimeAndImageId)
    }

    override fun onSuccess(weatherResponse: List<Interval>) {
        meanView?.setupIntervals(weatherResponse)
        startTimeAndImageId = weatherResponse.associate { it.startTime to it.clothesImageId }
    }

    override fun onError(message: String) {
        meanView?.showError(message)
    }

}