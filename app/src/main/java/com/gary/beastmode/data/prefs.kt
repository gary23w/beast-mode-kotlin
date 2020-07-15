package com.gary.beastmode.data

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val WORKOUTTIME = ""
    val RESTTIME = ""
    val INTERVALTIME = ""
    var prefs: SharedPreferences = context.getSharedPreferences("WORKOUT", Context.MODE_PRIVATE);
    var prefstwo: SharedPreferences = context.getSharedPreferences("REST", Context.MODE_PRIVATE);
    var prefsthree: SharedPreferences = context.getSharedPreferences("INTERVAL", Context.MODE_PRIVATE);
    var stats: SharedPreferences = context.getSharedPreferences("STATS", Context.MODE_PRIVATE);
    var workoutPref: String?
        get() = prefs.getString(WORKOUTTIME, WORKOUTTIME)
        set(value) = prefs.edit().putString(WORKOUTTIME, value).apply()
    var restPref: String?
        get() = prefstwo.getString(RESTTIME, RESTTIME)
        set(value) = prefstwo.edit().putString(RESTTIME, value).apply()
    var intervalPref: String?
    get() = prefsthree.getString(INTERVALTIME, INTERVALTIME)
    set(value) = prefsthree.edit().putString(INTERVALTIME, value).apply()
    ///////////////////STATS//////////////////
    var intervalPrefCount: Int?
        get() = stats.getInt("FRIGGING_INTERVALS", 0)
        set(value) = stats.edit().putInt("FRIGGING_INTERVALS", value!!).apply()
    var bmiSharedPref: Int?
        get() = stats.getInt("FRIGGING_BMI", 0)
        set(value) = stats.edit().putInt("FRIGGING_BMI", value!!).apply()
    var quitButtonPref: Int?
        get() = stats.getInt("FRIGGING_QUIT", 0)
        set(value) = stats.edit().putInt("FRIGGING_QUIT", value!!).apply()
}