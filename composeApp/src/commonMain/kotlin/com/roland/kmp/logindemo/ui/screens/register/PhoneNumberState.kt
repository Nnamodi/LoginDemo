package com.roland.kmp.logindemo.ui.screens.register

import com.roland.kmp.logindemo.ui.sheet.CountryCodeItemModel

data class PhoneNumberState(
    val flagEmoji: String = "",
    val countryCode: String = "",
    val dialCode: String = "",
    val phoneNumber: String = "",
    val countries: List<CountryCodeItemModel> = emptyList()
)
