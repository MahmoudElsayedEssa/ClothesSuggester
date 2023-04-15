package com.example.clothessuggester.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PrefsUtil {
    private var sharedPrefs: SharedPreferences? = null
    private const val START_TIME_AND_IMAGE_ID = "StartTimeAndImageId"
    const val SHARED_PREFS_NAME = "MySharedPrefs"

    fun initPrefs(sharedPref: SharedPreferences) {
        sharedPrefs = sharedPref
    }

    var startTimeAndImageId: Map<String, Int>
        get() {
            val jsonString = sharedPrefs?.getString(START_TIME_AND_IMAGE_ID, null)
            return if (jsonString != null) {
                Gson().fromJson(jsonString, object : TypeToken<Map<String, Int>>() {}.type)
            } else {
                emptyMap()
            }
        }
        set(value) {
            val jsonString = Gson().toJson(value)
            sharedPrefs?.edit()?.putString(START_TIME_AND_IMAGE_ID, jsonString)?.apply()
        }

}
