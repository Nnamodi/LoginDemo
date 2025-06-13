package com.roland.kmp.logindemo.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roland.kmp.logindemo.domain.repo.UserRepository
import com.roland.kmp.logindemo.domain.repo.UtilRepository
import com.roland.kmp.logindemo.ui.navigation.AppRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class HomeViewModel : ViewModel(), KoinComponent {
	private val userRepository by inject<UserRepository>()
	private val utilRepository by inject<UtilRepository>()

	private val _username = MutableStateFlow("")
	var username by mutableStateOf(_username.value); private set
	var loggedIn by mutableStateOf(false); private set
	var startDestination by mutableStateOf(AppRoute.Login.route); private set

	init {
		viewModelScope.launch {
			loggedIn = utilRepository.getLoginStatus()
			startDestination = if (loggedIn) AppRoute.Home.route else AppRoute.Login.route
		}
		viewModelScope.launch {
			_username.value = userRepository.getUserInfo().firstName
		}
		viewModelScope.launch {
			_username.collect {
				username = it
			}
		}
	}

	fun actions(action: HomeActions) {
		when (action) {
			HomeActions.Logout -> logout()
		}
	}

	private fun logout() {
		viewModelScope.launch {
			loggedIn = !utilRepository.saveLoginStatus(false)
		}
	}
}