package com.roland.kmp.logindemo.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roland.kmp.logindemo.ui.navigation.Screens

@Composable
fun LoginScreen(navigate: (Screens) -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "Sign in",
			modifier = Modifier.padding(20.dp),
			fontSize = 26.sp,
			fontWeight = FontWeight.Medium
		)
		Spacer(Modifier.height(150.dp))
		Button(
			onClick = { navigate(Screens.Register(LoginType.Register)) },
			modifier = Modifier
				.fillMaxWidth()
				.padding(30.dp),
			shape = CircleShape
		) {
			Text("Register")
		}
		OutlinedButton(
			onClick = { navigate(Screens.Register(LoginType.Login)) },
			modifier = Modifier
				.fillMaxWidth()
				.padding(30.dp),
			shape = CircleShape
		) {
			Text("Log in")
		}
	}
}

enum class LoginType { Register, Login }