package com.roland.kmp.logindemo.data.sharedPref

import platform.Foundation.NSUserDefaults

class CoreSharedStorage(
    private val userDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults()
) : AppSharedPrefProvider {

    override fun get(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    override fun set(key: String, value: String): Boolean {
        userDefaults.setObject(value = value, forKey = key)
        return true
    }

    override fun remove(key: String): Boolean {
        userDefaults.removeObjectForKey(key)
        return true
    }

}