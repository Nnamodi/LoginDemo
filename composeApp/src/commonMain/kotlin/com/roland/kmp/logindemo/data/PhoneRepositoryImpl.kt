package com.roland.kmp.logindemo.data

import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.repo.PhoneRepository
import com.google.i18n.phonenumbers.PhoneNumberUtil

class PhoneRepositoryImpl : PhoneRepository {
	override fun verifyNumber(phoneNumber: PhoneNumber): Boolean {
		val phoneUtil = PhoneNumberUtil
	}
}