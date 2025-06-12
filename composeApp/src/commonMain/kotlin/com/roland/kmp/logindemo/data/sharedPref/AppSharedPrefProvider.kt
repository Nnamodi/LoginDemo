package com.roland.kmp.logindemo.data.sharedPref

interface AppSharedPrefProvider {
    fun get(key: String): String?
    fun set(key: String, value: String): Boolean
    fun remove(key: String): Boolean
}

expect class AppContext constructor(any: Any) {}

expect fun AppContext.buildSharedPref(storageFileName: String): AppSharedPrefProvider