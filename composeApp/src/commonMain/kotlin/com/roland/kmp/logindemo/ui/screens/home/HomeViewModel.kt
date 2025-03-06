package com.roland.kmp.logindemo.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roland.kmp.logindemo.domain.repo.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
	private val userRepository by inject<UserRepository>()

	private val _username = MutableStateFlow("")
	var username by mutableStateOf(_username.value); private set
	var loggedOut by mutableStateOf(false); private set

	init {
		viewModelScope.launch {
			userRepository.getUserInfo().collect {
				_username.value = it.username
			}
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
			loggedOut = userRepository.logoutUser()
		}
	}
}