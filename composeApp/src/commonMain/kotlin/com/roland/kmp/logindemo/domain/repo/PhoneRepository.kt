package com.roland.kmp.logindemo.domain.repo

import com.roland.kmp.logindemo.domain.model.PhoneNumber

interface PhoneRepository {

	fun verifyNumber(phoneNumber: PhoneNumber): Boolean

	fun getAsYouTypeFormatter(newInput: Char, regionCode: String): String

}