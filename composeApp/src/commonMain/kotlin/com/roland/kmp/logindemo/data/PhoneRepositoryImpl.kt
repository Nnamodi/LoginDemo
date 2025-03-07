package com.roland.kmp.logindemo.data

import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.repo.PhoneRepository
import utils.NumberParseException
import utils.PhoneNumberUtil

class PhoneRepositoryImpl : PhoneRepository {

	override fun verifyNumber(phoneNumber: PhoneNumber): Boolean {
		val phoneUtil = PhoneNumberUtil.getInstance()
		return try {
			val parsedNumber = phoneUtil.parse(phoneNumber.number, phoneNumber.countryCode)
			phoneUtil.isValidNumber(parsedNumber)
		} catch (e: NumberParseException) {
			false
		}
	}

}