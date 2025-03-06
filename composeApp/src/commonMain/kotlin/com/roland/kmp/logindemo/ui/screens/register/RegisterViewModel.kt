package com.roland.kmp.logindemo.ui.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.model.User
import com.roland.kmp.logindemo.domain.repo.PhoneRepository
import com.roland.kmp.logindemo.domain.repo.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel : ViewModel(), KoinComponent {
	private val userRepository by inject<UserRepository>()
	private val phoneRepository by inject<PhoneRepository>()

	private val _requestState = MutableStateFlow(RequestState())
	var requestState by mutableStateOf(_requestState.value); private set

	private val _inputCheck = MutableStateFlow(InputCheck())
	var inputCheck by mutableStateOf(_inputCheck.value); private set

	private var user by mutableStateOf<User?>(null)

	init {
		viewModelScope.launch {
			userRepository.getUserInfo().collect {
				user = it
			}
		}
		viewModelScope.launch {
			_requestState.collect {
				requestState = it
			}
		}
		viewModelScope.launch {
			_inputCheck.collect {
				inputCheck = it
			}
		}
	}

	fun actions(action: Actions) {
		when (action) {
			is Actions.Login -> login(action.username, action.password)
			is Actions.Register -> register(action.user)
			is Actions.ResetInputErrorState -> resetInputErrorState(action.input)
		}
	}

	private fun login(username: String, password: String) {
		viewModelScope.launch {
			_requestState.update { it.copy(loading = true) }
			val ready = readyToLogin(username, password)
			delay(2000)
			_requestState.update { it.copy(
				loading = false,
				success = ready,
				error = "Incorrect credentials".takeIf { !ready }
			) }
		}
	}

	private fun register(user: User) {
		viewModelScope.launch {
			_requestState.update { it.copy(loading = true) }
			val ready = readyToRegister(user)
			delay(2000)
			_requestState.update { it.copy(loading = false, success = ready) }
			if (!ready) return@launch
			val registered = userRepository.registerUser(user)
			_requestState.update { it.copy(loading = false, success = registered) }
		}
	}

	private fun resetInputErrorState(input: Input) {
		when (input) {
			Input.FirstName -> _inputCheck.update { it.copy(firstNameIsError = false) }
			Input.LastName -> _inputCheck.update { it.copy(lastNameIsError = false) }
			Input.Username -> _inputCheck.update { it.copy(usernameIsError = false) }
			Input.Phone -> {
				_inputCheck.update { it.copy(phoneNumberIsError = false) }
				_requestState.value = RequestState()
			}
			Input.Password -> _inputCheck.update { it.copy(passwordIsError = false) }
		}
	}

	private fun readyToLogin(username: String, password: String): Boolean {
		return usernameIsValid(username)
				&& username == user?.username
				&& passwordIsValid(password)
				&& password == user?.password
	}

	private fun readyToRegister(user: User): Boolean {
		return firstNameIsValid(user.firstName)
				&& lastNameIsValid(user.lastName)
				&& usernameIsValid(user.username)
				&& phonenumberIsValid(user.phoneNumber)
				&& passwordIsValid(user.password)
	}

	private fun firstNameIsValid(firstName: String): Boolean {
		val isValid = firstName.isNotEmpty()
		_inputCheck.update { it.copy(firstNameIsError = !isValid) }
		return isValid
	}

	private fun lastNameIsValid(lastName: String): Boolean {
		val isValid = lastName.isNotEmpty()
		_inputCheck.update { it.copy(lastNameIsError = !isValid) }
		return isValid
	}

	private fun usernameIsValid(username: String): Boolean {
		val isValid = username.isNotEmpty()
		_inputCheck.update { it.copy(usernameIsError = !isValid) }
		return isValid
	}

	private fun phonenumberIsValid(phonenumber: PhoneNumber): Boolean {
		val isValid = phonenumber.countryCode.isNotEmpty() && phonenumber.number.isNotEmpty()
		val isVerified = phoneRepository.verifyNumber(phonenumber)
		if (!isVerified) _requestState.update { it.copy(error = "Enter a valid phone number") }
		_inputCheck.update { it.copy(phoneNumberIsError = !(isValid && isVerified)) }
		return isValid
	}

	private fun passwordIsValid(password: String): Boolean {
		val isValid = password.isNotEmpty()
		_inputCheck.update { it.copy(passwordIsError = !isValid) }
		return isValid
	}
}