package com.cst.taipeitour.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun set(
        key: String,
        value: String
    ) {
        sp.edit().putString(key, value).apply()
    }

    fun getString(
        key: String
    ) = sp.getString(key, "")!!

    fun getString(
        key: String,
        default: String
    ) = sp.getString(key, default)!!
}