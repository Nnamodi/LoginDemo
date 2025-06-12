package com.roland.kmp.logindemo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
	username: String,
	actions: (HomeActions) -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "Welcome home, $username",
			modifier = Modifier.padding(30.dp),
			fontSize = 24.sp,
			fontWeight = FontWeight.Medium
		)
		Spacer(Modifier.height(150.dp))
		Button(
			onClick = { actions(HomeActions.Logout) },
			modifier = Modifier
				.fillMaxWidth()
				.padding(30.dp),
			shape = MaterialTheme.shapes.medium
		) {
			Text("Logout")
		}
	}
}