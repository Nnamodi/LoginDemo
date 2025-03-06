package com.roland.kmp.logindemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.roland.kmp.logindemo.ui.navigation.NavGraph
import com.roland.kmp.logindemo.ui.theme.LoginDemoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    LoginDemoTheme {
        KoinContext {
            val navController = rememberNavController()
            NavGraph(navController)
        }
    }
}