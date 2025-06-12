package com.roland.kmp.logindemo.data.sharedPref

import android.content.Context

class CoreSharedStorage(context: Context, prefName: String) : AppSharedPrefProvider {

    private val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun get(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun set(key: String, value: String): Boolean {
        return sharedPreferences.edit().apply {
            putString(key, value)
        }.commit()
    }

    override fun remove(key: String): Boolean {
        return sharedPreferences.edit().apply {
            remove(key)
        }.commit()
    }

}