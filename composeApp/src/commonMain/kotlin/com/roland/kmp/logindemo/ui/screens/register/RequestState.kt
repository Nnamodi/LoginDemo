package com.roland.kmp.logindemo.ui.screens.register

data class RequestState(
	val loading: Boolean = false,
	val success: Boolean = false,
	val error: String? = null
)
