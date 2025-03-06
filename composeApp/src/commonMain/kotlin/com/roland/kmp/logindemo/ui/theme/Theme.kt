package com.roland.kmp.logindemo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun LoginDemoTheme(content: @Composable () -> Unit) {
	MaterialTheme(
		shapes = Shapes,
		content = content
	)
}