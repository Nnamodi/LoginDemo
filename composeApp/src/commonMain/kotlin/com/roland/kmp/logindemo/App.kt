package com.roland.kmp.logindemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.roland.kmp.logindemo.ui.navigation.NavGraph
import com.roland.kmp.logindemo.ui.screens.home.HomeViewModel
import com.roland.kmp.logindemo.ui.theme.LoginDemoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    LoginDemoTheme {
        KoinContext {
            val navController = rememberNavController()
            val homeViewModel: HomeViewModel = koinViewModel()
            NavGraph(
                navController = navController,
                startDestination = homeViewModel.startDestination
            )
        }
    }
}