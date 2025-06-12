package com.roland.kmp.logindemo.domain.model

data class User(
	val firstName: String,
	val lastName: String,
	val username: String,
	val phoneNumber: PhoneNumber,
	val password: String
) {
	companion object {
		val DEFAULT = User("", "", "", PhoneNumber.DEFAULT, "")
	}
}

data class PhoneNumber(
	val countryCode: String,
	val number: String
) {
	companion object {
		val DEFAULT = PhoneNumber("", "")
	}
}
