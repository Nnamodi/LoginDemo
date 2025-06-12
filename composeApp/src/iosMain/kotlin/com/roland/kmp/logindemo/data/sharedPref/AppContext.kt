package com.roland.kmp.logindemo.data.sharedPref

actual class AppContext actual constructor(any: Any)

actual fun AppContext.buildSharedPref(storageFileName: String): AppSharedPrefProvider {
	return CoreSharedStorage()
}