package com.greenmug.android.buyerguide.utils

import android.content.Context
import android.content.SharedPreferences


class PreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences;

    init {
        sharedPreferences =
            context.getSharedPreferences(Constants.KEY_PREFERENCE_FILTER, Context.MODE_PRIVATE)
    }


    fun putBoolean(key: String?, value: Boolean?) {
        sharedPreferences.edit().putBoolean(key, value!!).apply()
    }

    fun putInt(key: String?, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String?): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putString(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String?): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }


}