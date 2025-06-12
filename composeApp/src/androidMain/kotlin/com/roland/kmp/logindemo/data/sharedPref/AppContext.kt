package com.roland.kmp.logindemo.data.sharedPref

import android.content.Context

actual class AppContext actual constructor(any: Any) {
    var context: Context
        private set

    init {
        context = any as Context
    }
}

actual fun AppContext.buildSharedPref(storageFileName: String): AppSharedPrefProvider {
    return CoreSharedStorage(context, storageFileName)
}