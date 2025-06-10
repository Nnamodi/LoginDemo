package com.roland.kmp.logindemo.locale_provider

interface LocaleProvider {
    fun getLocaleDefault() : String
    fun getLocaleIsoCountries(countryCode: String): String
    fun getFlagEmoji(countryCode: String) : String

    companion object {
        fun create(): LocaleProvider = buildLocaleProvider()
    }
}

expect fun buildLocaleProvider() : LocaleProvider