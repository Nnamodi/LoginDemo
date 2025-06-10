package com.roland.kmp.logindemo.locale_provider

import java.util.Locale

class LocalProviderImpl : LocaleProvider {

    override fun getLocaleDefault(): String {
        return Locale.getDefault().country
    }

    override fun getLocaleIsoCountries(countryCode: String): String {
        return Locale("", countryCode).displayCountry
    }

    override fun getFlagEmoji(countryCode: String): String {
        val locale = Locale("", countryCode)
        return with(locale) {
            val firstLetter = Character.codePointAt(country, 0) - 0x41 + 0x1F1E6
            val secondLetter = Character.codePointAt(country, 1) - 0x41 + 0x1F1E6
            String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
        }
    }

}

actual fun buildLocaleProvider(): LocaleProvider = LocalProviderImpl()