package com.example.clothessuggester.data.network

import com.example.clothessuggester.data.network.Constants.APIKEY
import com.example.clothessuggester.data.network.Constants.BASE_URL
import com.example.clothessuggester.data.network.Constants.CAIRO_LOCATION
import com.example.clothessuggester.data.network.Constants.CAIRO_TIME_ZONE
import com.example.clothessuggester.data.network.Constants.CELSIUS_UNITS
import com.example.clothessuggester.data.network.Constants.FIELDS
import com.example.clothessuggester.data.network.Constants.HTTPS_SCHEME
import com.example.clothessuggester.data.network.Constants.LOCATION
import com.example.clothessuggester.data.network.Constants.MIN_MAX_TEMPERATURE
import com.example.clothessuggester.data.network.Constants.ONE_DAY_TIME_STEPS
import com.example.clothessuggester.data.network.Constants.TIME_LINES
import com.example.clothessuggester.data.network.Constants.TIME_STEPS
import com.example.clothessuggester.data.network.Constants.TIME_ZONE
import com.example.clothessuggester.data.network.Constants.UNITS
import okhttp3.*
import java.io.IOException

class TomorrowApiClient(
    private val apiKey: String,
    private val client: OkHttpClient = OkHttpClient()
) {

    fun getIntervals(
        location: String = CAIRO_LOCATION,
        timesteps: String = ONE_DAY_TIME_STEPS,
        units: String = CELSIUS_UNITS,
        timezone: String = CAIRO_TIME_ZONE,
        previousClothesImage: Map<String, Int>,
        callback: WeatherCallback
    ) {
        val url = HttpUrl.Builder()
            .scheme(HTTPS_SCHEME)
            .host(BASE_URL)
            .addPathSegment("v4")
            .addPathSegment(TIME_LINES)
            .addQueryParameter(LOCATION, location)
            .addQueryParameter(FIELDS, MIN_MAX_TEMPERATURE)
            .addQueryParameter(TIME_STEPS, timesteps)
            .addQueryParameter(UNITS, units)
            .addQueryParameter(APIKEY, apiKey)
            .addQueryParameter(TIME_ZONE, timezone)
            .build()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.message?.let { callback.onError(it) }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val intervals = jsonString?.let { parseIntervals(it, previousClothesImage) }
                    if (intervals != null) {
                        callback.onSuccess(intervals)
                    }
                }
            }
        })
    }


}

