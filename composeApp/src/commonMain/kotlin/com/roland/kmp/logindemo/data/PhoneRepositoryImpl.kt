package com.roland.kmp.logindemo.data

import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.repo.PhoneRepository
import utils.NumberParseException
import utils.PhoneNumberUtil

class PhoneRepositoryImpl : PhoneRepository {
	val phoneUtil = PhoneNumberUtil.getInstance()

	override fun verifyNumber(phoneNumber: PhoneNumber): Boolean {
		return try {
			val parsedNumber = phoneUtil.parse(phoneNumber.number, phoneNumber.countryCode)
			phoneUtil.isValidNumber(parsedNumber)
		} catch (e: NumberParseException) {
			false
		}
	}

	override fun getAsYouTypeFormatter(newInput: Char, regionCode: String): String {
		val formatter = phoneUtil.getAsYouTypeFormatter(regionCode)
		return formatter.inputDigit(newInput)
	}

}