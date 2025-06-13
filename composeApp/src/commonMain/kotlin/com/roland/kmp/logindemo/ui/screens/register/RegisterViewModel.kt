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
import com.roland.kmp.logindemo.domain.repo.UtilRepository
import com.roland.kmp.logindemo.locale_provider.CountryLocale
import com.roland.kmp.logindemo.locale_provider.LocaleProvider
import com.roland.kmp.logindemo.ui.sheet.CountryCodeItemModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel : ViewModel(), KoinComponent {
	private val userRepository by inject<UserRepository>()
	private val utilRepository by inject<UtilRepository>()
	private val phoneRepository by inject<PhoneRepository>()
	private val localeProvider = LocaleProvider.create()

	private val _phoneNumberState = MutableStateFlow(PhoneNumberState())
	var phoneNumberState by mutableStateOf(_phoneNumberState.value); private set

	private val _requestState = MutableStateFlow(RequestState())
	var requestState by mutableStateOf(_requestState.value); private set

	private val _inputCheck = MutableStateFlow(InputCheck())
	var inputCheck by mutableStateOf(_inputCheck.value); private set

	private var user by mutableStateOf<User?>(null)

	init {
		viewModelScope.launch {
			user = userRepository.getUserInfo()
		}
		viewModelScope.launch {
			_phoneNumberState.update { it.copy(countries = toCountryListItem(DEFAULT_LOCALE)) }
			onCountryCodeSelected(DEFAULT_LOCALE)
		}
		viewModelScope.launch {
			_phoneNumberState.collect {
				phoneNumberState = it
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
			is Actions.OnCountrySelected -> onCountryCodeSelected(action.countryCode)
			is Actions.OnPhoneNumberChanged -> onPhoneNumberChanged(action.newValue)
			is Actions.Login -> login(action.username, action.password)
			is Actions.Register -> register(action.user)
			is Actions.ResetInputErrorState -> resetInputErrorState(action.input)
		}
	}

	private fun onCountryCodeSelected(countryCode: String) {
		val flagEmoji = localeProvider.getFlagEmoji(countryCode)
		val dialCode = CountryLocale.getCountryDialingCode(countryCode)
		_phoneNumberState.update { it.copy(
			flagEmoji = flagEmoji,
			countryCode = countryCode,
			dialCode = "$dialCode",
			countries = toCountryListItem(countryCode)
		) }
	}

	private fun onPhoneNumberChanged(newValue: String) {
		val phoneNumber = phoneRepository.getAsYouTypeFormatter(
            newInput = newValue.last(),
            regionCode = phoneNumberState.countryCode
		)
		_phoneNumberState.update { it.copy(phoneNumber = newValue) }
	}

	private fun login(username: String, password: String) {
		viewModelScope.launch {
			_inputCheck.value = InputCheck()
			_requestState.update { it.copy(loading = true, error = null) }
			delay(2000)
			val ready = readyToLogin(username, password)
			_requestState.update { it.copy(
				loading = false,
				success = ready,
				error = "Incorrect credentials".takeIf { !ready }
			) }
			if (!ready) {
				_inputCheck.update { it.copy(usernameIsError = true, passwordIsError = true) }
				return@launch
			}
			utilRepository.saveLoginStatus(isLaunched = true)
		}
	}

	private fun register(user: User) {
		viewModelScope.launch {
			_requestState.update { it.copy(loading = true) }
			delay(2000)
			val ready = readyToRegister(user)
			_requestState.update { it.copy(loading = false, success = ready) }
			if (!ready) return@launch
			val registered = userRepository.registerUser(user)
			_requestState.update { it.copy(loading = false, success = registered) }
			utilRepository.saveLoginStatus(isLaunched = registered)
		}
	}

	private fun resetInputErrorState(input: Input) {
		when (input) {
			Input.FirstName -> _inputCheck.update { it.copy(firstNameIsError = false) }
			Input.LastName -> _inputCheck.update { it.copy(lastNameIsError = false) }
			Input.Username -> _inputCheck.update { it.copy(usernameIsError = false) }
			Input.Phone -> _inputCheck.update { it.copy(phoneNumberIsError = false) }
			Input.Password -> _inputCheck.update { it.copy(passwordIsError = false) }
		}
        if (input != Input.Phone && input != Input.Password) return
		_requestState.value = RequestState()
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
				&& phoneNumberIsValid(user.phoneNumber)
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

	private fun phoneNumberIsValid(phoneNumber: PhoneNumber): Boolean {
		val isValid = phoneNumber.countryCode.isNotEmpty() && phoneNumber.number.isNotEmpty()
		val isVerified = phoneRepository.verifyNumber(phoneNumber)
		if (!isVerified) _requestState.update { it.copy(error = "Enter a valid phone number") }
		_inputCheck.update { it.copy(phoneNumberIsError = !(isValid && isVerified)) }
		return isValid && isVerified
	}

	private fun passwordIsValid(password: String): Boolean {
		val isValid = password.isNotEmpty()
		_inputCheck.update { it.copy(passwordIsError = !isValid) }
		return isValid
	}

	private fun toCountryListItem(currentCountryCode: String) : List<CountryCodeItemModel> {
		val sortedCountriesPair = CountryLocale.getCountryCodes().map { countryCode ->
			countryCode to localeProvider.getLocaleIsoCountries(countryCode)
		}.sortedBy { it.second }
		return sortedCountriesPair.map { (countryCode, countryName) ->
			val dialCode = CountryLocale.getCountryDialingCode(countryCode)
			CountryCodeItemModel(
				flagEmoji = localeProvider.getFlagEmoji(countryCode),
				title = "$dialCode",
				body = countryName,
				countryCode = countryCode,
				isChecked = countryCode == currentCountryCode
			)
		}
	}
}

private const val DEFAULT_LOCALE = "NG"