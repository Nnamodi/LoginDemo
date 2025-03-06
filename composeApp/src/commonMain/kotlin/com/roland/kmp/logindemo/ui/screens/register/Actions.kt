package com.roland.kmp.logindemo.ui.screens.register

import com.roland.kmp.logindemo.domain.model.User

sealed class Actions {

	data class Register(val user: User) : Actions()

	data class Login(
		val username: String,
		val password: String
	) : Actions()

	data class ResetInputErrorState(val input: Input) : Actions()

}