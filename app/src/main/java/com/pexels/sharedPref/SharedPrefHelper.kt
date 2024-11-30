package com.pexels.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.pexels.AppMain

class SharedPrefHelper {
    private val sharedPrefKey = "SEARCH_HISTORY_PREF"
    private val historyKey = "HISTORY"



    private var sharedPreferences: SharedPreferences =  AppMain.getContext()
        .getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)

    fun saveHistory(query: String) {

        val history = getSearchHistory().toMutableList()

        if (!history.contains(query)) {
            history.add(0, query) // Add new item to the beginning
        }

        // Keep only the latest 10 items
        if (history.size > 5) {
            history.removeAt(history.size - 1) // Remove the last item manually
        }
        sharedPreferences.edit().putStringSet(historyKey, history.toSet()).apply()

    }


    // get all query history from shared preferences
    fun getSearchHistory(): List<String> {
        return sharedPreferences.getStringSet(historyKey, emptySet())?.toList() ?: emptyList()
    }

    fun clearSharePref() {
        sharedPreferences.edit().clear().apply()
    }
}
