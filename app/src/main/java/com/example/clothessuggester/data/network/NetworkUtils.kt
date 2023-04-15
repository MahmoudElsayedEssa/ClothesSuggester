package com.example.clothessuggester.data.network

import com.example.clothessuggester.R
import com.example.clothessuggester.data.network.Constants.RESPONSE_DATA
import com.example.clothessuggester.data.network.Constants.RESPONSE_INTERVALS
import com.example.clothessuggester.data.network.Constants.RESPONSE_MAX_TEMPERATURE
import com.example.clothessuggester.data.network.Constants.RESPONSE_MIN_TEMPERATURE
import com.example.clothessuggester.data.network.Constants.RESPONSE_START_TIME
import com.example.clothessuggester.data.network.Constants.RESPONSE_VALUES
import com.example.clothessuggester.data.network.Constants.TIME_LINES
import com.projects.whattowear.model.DayWeatherType
import com.projects.whattowear.model.Interval
import com.projects.whattowear.model.Temperature
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


fun parseIntervals(response: String, previousClothesImage: Map<String, Int>): List<Interval> {
    val jasonIntervals =
        JSONObject(response)
            .getJSONObject(RESPONSE_DATA)
            .getJSONArray(TIME_LINES)
            .getJSONObject(0)
            .getJSONArray(RESPONSE_INTERVALS)

    val intervals = mutableListOf<Interval>()
    for (i in 0 until jasonIntervals.length()) {
        val interval = jasonIntervals.getJSONObject(i)
        val startTime = interval.getString(RESPONSE_START_TIME)
        val temperature = getTemperature(interval)
        val weatherType = getDayWeatherType(temperature)
        val weatherImageId = getWeatherImageId(weatherType)
        val clothesImageId =
            getClothesImageId(startTime, weatherType, intervals, previousClothesImage)
        intervals.add(Interval(startTime, temperature, weatherType, weatherImageId, clothesImageId))
    }
//    PrefsUtil.startTimeAndImageId = intervals.associate { it.startTime to it.clothesImageId }
    return intervals
}

private fun getTemperature(interval: JSONObject): Temperature {
    val values = interval.getJSONObject(RESPONSE_VALUES)
    return Temperature(
        temperatureMax = values.getString(RESPONSE_MAX_TEMPERATURE).toDouble(),
        temperatureMin = values.getString(RESPONSE_MIN_TEMPERATURE).toDouble(),
    )
}

private fun getClothesImageId(
    startTime: String,
    weatherType: DayWeatherType,
    intervals: List<Interval>,
    previousClothesImage: Map<String, Int>
): Int {
    val nextInterval = intervals.firstOrNull { it.startTime > startTime }
    return if (nextInterval != null) {
        previousClothesImage[startTime] ?: previousClothesImage[nextInterval.startTime]
        ?: getClothesImageId(nextInterval.startTime, weatherType, intervals, previousClothesImage)
    } else {
        previousClothesImage[startTime] ?: getClothesImageId(weatherType, intervals)
    }
}


fun getClothesList(dayWeatherType: DayWeatherType) = when (dayWeatherType) {
    DayWeatherType.COLD -> listOf(
        R.drawable.winter_1,
        R.drawable.winter_2,
        R.drawable.winter_3,
        R.drawable.winter_4,
        R.drawable.winter_5,
        R.drawable.winter_6,
    )
    else -> listOf(
        R.drawable.summer_1,
        R.drawable.summer_2,
        R.drawable.summer_3,
        R.drawable.summer_4,
        R.drawable.summer_5,
        R.drawable.summer_6,
    )
}


fun getWeatherImageId(dayWeatherType: DayWeatherType) = when (dayWeatherType) {
    DayWeatherType.COLD -> R.drawable.svg_cold
    else -> R.drawable.svg_hot
}


fun getClothesImageId(dayWeatherType: DayWeatherType, intervals: List<Interval>) =
    if (intervals.isNotEmpty() && dayWeatherType == intervals.last().weatherType) {
        (getClothesList(dayWeatherType) - intervals.last().clothesImageId).random()
    } else {
        getClothesList(dayWeatherType).random()
    }


fun getDayWeatherType(temperature: Temperature) = when {
    temperature.temperatureMax < 25.0 -> DayWeatherType.COLD
    else -> DayWeatherType.HOT
}



fun getDayName(dateString: String, formatPattern: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateString)
    val calendar = Calendar.getInstance()
    calendar.time = date!!
    return SimpleDateFormat(formatPattern, Locale.getDefault()).format(date)
}


//private fun saveStartTimeAndImageId(values: List<Pair<String, Int>>) {
//    val delimiter1 = "|"
//    val delimiter2 = ","
//    val serializedPairs = values.joinToString(delimiter1) { "${it.first}$delimiter2${it.second}" }
//    PrefsUtil.startTimeAndImageId = serializedPairs
//
//}
//
//private fun getStartTimeAndImageId(): List<Pair<String, Int>>? {
//    val delimiter1 = "|"
//    val delimiter2 = ","
//    val serializedPairsArray = PrefsUtil.startTimeAndImageId?.split(delimiter1)?.toTypedArray()
//    return serializedPairsArray?.map { serializedPair ->
//        val pairValues = serializedPair.split(delimiter2)
//        Pair(pairValues[0], pairValues[1].toInt())
//    }
//}
