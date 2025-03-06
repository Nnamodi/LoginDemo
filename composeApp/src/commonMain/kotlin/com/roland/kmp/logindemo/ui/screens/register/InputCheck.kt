package com.roland.kmp.logindemo.ui.screens.register

data class InputCheck(
	val firstNameIsError: Boolean = false,
	val lastNameIsError: Boolean = false,
	val usernameIsError: Boolean = false,
	val phoneNumberIsError: Boolean = false,
	val passwordIsError: Boolean = false,
)

enum class Input { FirstName, LastName, Username, Phone, Password }