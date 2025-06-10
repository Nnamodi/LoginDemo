package com.roland.kmp.logindemo.ui.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.model.User
import com.roland.kmp.logindemo.ui.components.CountrySelection
import com.roland.kmp.logindemo.ui.components.CustomTextField
import com.roland.kmp.logindemo.ui.navigation.Screens
import com.roland.kmp.logindemo.ui.screens.login.LoginType
import com.roland.kmp.logindemo.ui.sheet.CountryPickerBottomSheet
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
	loginType: LoginType,
	phoneNumberState: PhoneNumberState,
	requestState: RequestState,
	inputCheck: InputCheck,
	actions: (Actions) -> Unit,
	navigate: (Screens) -> Unit
) {
	val (flagEmoji, countryCode, dialCode, phoneNumber, countries) = phoneNumberState
	val (firstNameIsError, lastNameIsError, usernameIsError, phoneNumberIsError, passwordIsError) = inputCheck
	val focusRequester = remember { FocusRequester() }
	val focusManager = LocalFocusManager.current
	val resetInputErrorState: (Input) -> Unit = { actions(Actions.ResetInputErrorState(it)) }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		var firstName by rememberSaveable { mutableStateOf("") }
		var lastName by rememberSaveable { mutableStateOf("") }
		var username by rememberSaveable { mutableStateOf("") }
		val showCountrySelection = rememberSaveable { mutableStateOf(false) }

		Text(
			text = if (loginType == LoginType.Register) "Register" else "Login",
			modifier = Modifier.padding(20.dp),
			fontSize = 26.sp,
			fontWeight = FontWeight.Medium
		)

		if (loginType == LoginType.Register) {
			CustomTextField(
				value = firstName,
				onValueChange = { firstName = it },
				label = "First name",
				focusRequester = focusRequester,
				isError = firstNameIsError,
				input = Input.FirstName,
				resetInputErrorState = resetInputErrorState,
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			)

			CustomTextField(
				value = lastName,
				onValueChange = { lastName = it },
				label = "Last name",
				focusRequester = focusRequester,
				isError = lastNameIsError,
				input = Input.LastName,
				resetInputErrorState = resetInputErrorState,
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			)
		}

		CustomTextField(
			value = username,
			onValueChange = { username = it },
			label = "Username",
			focusRequester = focusRequester,
			isError = usernameIsError,
			input = Input.Username,
			resetInputErrorState = resetInputErrorState,
			onNext = { focusManager.moveFocus(FocusDirection.Down) }
		)

		if (loginType == LoginType.Register) {
			CustomTextField(
				value = phoneNumber,
				onValueChange = { actions(Actions.OnPhoneNumberChanged(it)) },
				label = "",
				focusRequester = focusRequester,
				isError = phoneNumberIsError,
				errorMessage = requestState.error,
				input = Input.Phone,
				resetInputErrorState = resetInputErrorState,
				leadingContent = {
					CountrySelection(
						dialCode = dialCode,
						flagEmoji = flagEmoji,
						onClick = { showCountrySelection.value = true }
					)
				},
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			)
		}

		var password by rememberSaveable { mutableStateOf("") }
		val loginAction = {
			if (loginType == LoginType.Register) {
				val phoneNumber = PhoneNumber(countryCode, phoneNumber)
				val user = User(firstName, lastName, username, phoneNumber, password)
				actions(Actions.Register(user))
			} else {
				actions(Actions.Login(username, password))
			}
		}
		CustomTextField(
			value = password,
			onValueChange = { password = it },
			label = "Password",
			focusRequester = focusRequester,
			isError = passwordIsError,
			input = Input.Password,
			resetInputErrorState = resetInputErrorState,
			onDone = {
				focusManager.clearFocus()
				loginAction()
			}
		)
		Spacer(Modifier.weight(1f))
		Button(
			onClick = loginAction,
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 50.dp)
				.padding(30.dp),
			enabled = !requestState.loading,
			shape = MaterialTheme.shapes.medium,
			colors = buttonColors(containerColor = if (requestState.success) Color.Green else Color.Unspecified)
		) {
			if (requestState.loading) {
				CircularProgressIndicator(
					modifier = Modifier.size(24.dp),
					strokeWidth = 2.dp
				)
			} else {
				Text(if (loginType == LoginType.Register) "Register" else "Login")
			}
		}

		if (showCountrySelection.value) {
			CountryPickerBottomSheet(
				onDismissRequest = { showCountrySelection.value = false },
				countries = countries,
				onCountrySelected = {
					actions(Actions.OnCountrySelected(it))
					showCountrySelection.value = false
				}
			)
		}
	}

	LaunchedEffect(requestState) {
		if (requestState.success) {
			delay(2000)
			navigate(Screens.Home)
		}
	}
}