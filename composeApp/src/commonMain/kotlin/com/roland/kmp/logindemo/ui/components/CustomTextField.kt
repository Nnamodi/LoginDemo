package com.roland.kmp.logindemo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
	label: String,
	onValueChange: (String) -> Unit,
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
	leadingContent: @Composable (() -> Unit)? = null,
	onNext: (KeyboardActionScope.() -> Unit)? = null,
	onDone: (KeyboardActionScope.() -> Unit)? = null
) {
	OutlinedTextField(
		value = value,
		onValueChange = {
			onValueChange(it)
			if (isError) resetInputErrorState(input)
		},
		modifier = modifier
			.fillMaxWidth()
			.focusRequester(focusRequester)
			.padding(30.dp, 6.dp),
		label = { Text(label) },
		leadingIcon = leadingContent,
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

@Composable
fun CountrySelection(
	dialCode: String,
	flagEmoji: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Row(
			modifier = modifier
				.padding(start = 16.dp)
				.clickable { onClick() },
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = flagEmoji,
				modifier = Modifier
					.size(24.dp)
					.padding(end = 16.dp)
			)
			Icon(
				imageVector = Icons.Rounded.ArrowDropDown,
				contentDescription = null,
				modifier = Modifier.size(24.dp)
			)
		}
		Spacer(Modifier.width(4.dp))
		Text("($dialCode)")
	}
}