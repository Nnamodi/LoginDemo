package com.roland.kmp.logindemo.locale_provider

import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale
import platform.Foundation.localizedStringForCountryCode
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
class LocaleProviderImpl : LocaleProvider {

    override fun getLocaleDefault(): String {
        return NSLocale.currentLocale.countryCode ?: "NG"
    }

    override fun getLocaleIsoCountries(countryCode: String): String {
        val current = NSLocale(localeIdentifier = "en_US")
        return current.localizedStringForCountryCode(countryCode) ?: "$countryCode - NA"
    }

    override fun getFlagEmoji(countryCode: String): String {
        val base = 0x1F1E6
        var s = ""
        for (i in countryCode.uppercase()) {
            val unicode = base + i.code - 'A'.code
            s += Char.toChars(unicode).concatToString()
        }
        return s
    }

}

actual fun buildLocaleProvider(): LocaleProvider = LocaleProviderImpl()