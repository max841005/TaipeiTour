package com.cst.taipeitour.data.repositories

import com.cst.taipeitour.data.network.ApiService
import com.cst.taipeitour.data.network.SafeApiRequest
import com.cst.taipeitour.data.preferences.LANG
import com.cst.taipeitour.data.preferences.PreferenceProvider
import java.util.Locale

class MainRepository(
    private val api: ApiService,
    private val preference: PreferenceProvider
) : SafeApiRequest() {

    fun getLang(): Locale = when (preference.getString(LANG)) {
        "" -> Locale.getDefault()
        "zh-tw" -> Locale.TRADITIONAL_CHINESE
        "zh-cn" -> Locale.SIMPLIFIED_CHINESE
        "ja" -> Locale.JAPANESE
        "ko" -> Locale.KOREAN
        "es" -> Locale("es")
        "th" -> Locale("th")
        "vi" -> Locale("vi")
        else -> Locale("en")
    }

    fun getCurrentLangItem(): Int = when (preference.getString(LANG, Locale.getDefault().language)) {
        "zh" -> when (Locale.getDefault().country) {
            "TW" -> 0
            else -> 1
        }
        "zh-tw" -> 0
        "zh-cn" -> 1
        "ja" -> 3
        "ko" -> 4
        "es" -> 5
        "th" -> 6
        "vi" -> 7
        else -> 2
    }

    fun changeLang(
        lang: String
    ) {

        preference.set(LANG, lang)
    }

    suspend fun getList() = apiRequest { api.getList(getRequestLang()) }.data

    private fun getRequestLang(): String = when (preference.getString(LANG)) {

        "" -> when (Locale.getDefault().language) {
            "zh" -> when (Locale.getDefault().country) {
                "TW" -> "zh-tw"
                else -> "zh-cn"
            }
            "ja" -> "ja"
            "ko" -> "ko"
            "es" -> "es"
            "th" -> "th"
            "vi" -> "vi"
            else -> "en"
        }

        else -> preference.getString(LANG)
    }
}