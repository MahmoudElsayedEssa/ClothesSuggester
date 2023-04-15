package com.example.clothessuggester.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsUtils {
    private var sharedPreferences: SharedPreferences? = null
    private const val SHARED_PREF = "ClothPrefs"
    private val KEY = "clothes"

    fun initPrefUtil(context: Context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)

    }
    var clothName: String?
        get() = sharedPreferences?.getString(KEY,null)
        set(value) {
            sharedPreferences?.edit()?.putString(KEY,value)?.apply()
        }
}