package com.roland.kmp.logindemo.domain.model

data class User(
	val firstName: String,
	val lastName: String,
	val username: String,
	val phoneNumber: PhoneNumber,
	val password: String
)

data class PhoneNumber(
	val countryCode: String,
	val number: String
)
