package com.roland.kmp.logindemo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.None
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Words
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.roland.kmp.logindemo.ui.screens.register.Input

@Composable
fun CustomTextField(
	value: String,
	countryCode: String? = null,
	label: String,
	onValueChange: (String) -> Unit,
	onCodeValueChange: (String) -> Unit = {},
	modifier: Modifier = Modifier,
	focusRequester: FocusRequester,
	isError: Boolean = false,
	errorMessage: String? = null,
	input: Input,
	capitalization: KeyboardCapitalization = if (input in setOf(Input.Password, Input.Username)) None else Words,
	keyboardType: KeyboardType = when (input) {
		Input.Password -> KeyboardType.Password
		Input.Phone -> KeyboardType.Phone
		else -> KeyboardType.Text
	},
	imeAction: ImeAction = if (input == Input.Password) ImeAction.Done else ImeAction.Next,
	resetInputErrorState: (Input) -> Unit,
	onNext: (KeyboardActionScope.() -> Unit)? = null,
	onNextFromCode: (KeyboardActionScope.() -> Unit)? = null,
	onDone: (KeyboardActionScope.() -> Unit)? = null
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(30.dp, 6.dp)
	) {
		countryCode?.let {
			OutlinedTextField(
				value = value,
				onValueChange = {
					if (countryCode.length < 3) onCodeValueChange(it)
					if (isError) resetInputErrorState(input)
				},
				modifier = modifier
					.focusRequester(focusRequester)
					.padding(end = 16.dp),
				placeholder = { Text("234") },
				prefix = { Text("+") },
				isError = isError,
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Phone,
					imeAction = ImeAction.Next
				),
				keyboardActions = KeyboardActions(
					onNext = onNextFromCode
				),
				singleLine = true,
				shape = MaterialTheme.shapes.medium
			)
		}
		OutlinedTextField(
			value = value,
			onValueChange = {
				onValueChange(it)
				if (isError) resetInputErrorState(input)
			},
			modifier = modifier
				.weight(1f)
				.focusRequester(focusRequester),
			label = { Text(label) },
			supportingText = { errorMessage?.let { Text(it) } },
			isError = isError,
			keyboardOptions = KeyboardOptions(
				capitalization = capitalization,
				keyboardType = keyboardType,
				imeAction = imeAction
			),
			keyboardActions = KeyboardActions(
				onDone = onDone,
				onNext = onNext
			),
			singleLine = true,
			shape = MaterialTheme.shapes.medium
		)
	}
}