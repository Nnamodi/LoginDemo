package com.roland.kmp.logindemo.data

import com.roland.kmp.logindemo.data.sharedPref.AppSharedPrefProvider
import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.model.User
import com.roland.kmp.logindemo.domain.repo.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserRepositoryImpl : UserRepository, KoinComponent {
	private val sharedPref: AppSharedPrefProvider by inject()

	override fun getUserInfo(): User {
		val firstName = sharedPref.get(FIRST_NAME_KEY)
		val lastName = sharedPref.get(LAST_NAME_KEY)
		val username = sharedPref.get(USERNAME_KEY)
		val password = sharedPref.get(PASSWORD_KEY)
		val countryCode = sharedPref.get(COUNTRY_CODE_KEY)
		val phoneNumber = sharedPref.get(PHONE_NUMBER_KEY)
		return User(
            firstName = "$firstName",
			lastName = "$lastName",
            username = "$username",
            password = "$password",
			phoneNumber = PhoneNumber(
				countryCode = "$countryCode",
				number = "$phoneNumber"
			)
		)
	}

	override suspend fun registerUser(user: User): Boolean {
		val firstNameSaved = sharedPref.set(FIRST_NAME_KEY, user.firstName)
		val lastNameSaved = sharedPref.set(LAST_NAME_KEY, user.lastName)
		val usernameSaved = sharedPref.set(USERNAME_KEY, user.username)
		val passwordSaved = sharedPref.set(PASSWORD_KEY, user.password)
		val countryCodeSaved = sharedPref.set(COUNTRY_CODE_KEY, user.phoneNumber.countryCode)
		val phoneNumberSaved = sharedPref.set(PHONE_NUMBER_KEY, user.phoneNumber.number)

		return firstNameSaved && lastNameSaved && usernameSaved && passwordSaved && countryCodeSaved && phoneNumberSaved
	}

	companion object {
		const val FIRST_NAME_KEY = "first_name"
		const val LAST_NAME_KEY = "last_name"
		const val USERNAME_KEY = "username"
		const val PASSWORD_KEY = "password"

		const val COUNTRY_CODE_KEY = "country_code"
		const val PHONE_NUMBER_KEY = "phone_number"
	}
}